/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE6431;

/**
 *
 * @author kaushikmani
 */
public class Diner extends Utils implements Runnable, Comparable<Diner>{
    
    int dinerId;
    int arrivalTime;
    int tableAssigned;
    Order order;
    
    public Diner(){
        
    }

    public Diner(int dinerId, int arrivalTime, Order order) {
        this.dinerId = dinerId;
        this.arrivalTime = arrivalTime;
        this.order = order;
    }
    
    //Compareto wrapper
    @Override
    public int compareTo(Diner o) {
	if (arrivalTime > o.arrivalTime)
            return 1;
        else if (arrivalTime < o.arrivalTime)
            return -1;
	return 0;
    }
    
    @Override
    public void run(){
        
        //The diner has arrived
        arrive();
        
        try {
            //The diner books a table
            bookTable();
        } catch (InterruptedException ex) {
            System.out.println("Error while booking the table:" + ex.getMessage());
        }
        
        //The diner places the order
        placeOrder();
        
        try {
            //The diner is waiting for order
            waitForOrder();
        } catch (InterruptedException ex) {
            System.out.println("Error while waiting for the order" + ex.getMessage());
        }
        
        try {
            //The diner is eating the order
            eat();
        } catch (InterruptedException ex) {
            System.out.println("Error while eating the order" + ex.getMessage());
        }
        
        //The diner leaves after eating
        leave();
        
        //Check if the diner leaving is the last diner
        lastDinerLeaves();
    }
    
    //Print arrival time
    public void arrive(){
        System.out.println(getTime() + " - Diner " + dinerId + " arrives.");
    }
    
    //Book Table if available and print time at which the table is available
    public void bookTable() throws InterruptedException{
        synchronized(Restaurant.freeTables){
            while(Restaurant.freeTables.isEmpty()){
                Restaurant.freeTables.wait();
            } 
            tableAssigned = Restaurant.freeTables.remove(0);
            System.out.println(getTime() + " - Diner " + dinerId + " is seated at table " + tableAssigned + ".");
        }
    }
    
    //Place Order
    public void placeOrder(){
        synchronized(Restaurant.hungryDiners){
            Restaurant.hungryDiners.add(this);
            Restaurant.hungryDiners.notify();
        }
    }
    
    //Wait while the order is getting prepared
    public void waitForOrder() throws InterruptedException{
        synchronized(this){
            this.wait();
        }
    }
    
    //Eat the order - Every order takes 30 minutes
    public void eat() throws InterruptedException{
        System.out.println(getTime() + " - Diner " + dinerId + "'s order is ready. Diner " + dinerId + " starts eating.");
        Thread.sleep(3000);
    }
    
    //Leave the restaurant and free the table
    public void leave(){
        System.out.println(getTime() + " - Diner " + dinerId + " finishes. Diner " + dinerId + " leaves the restaurant.");
        synchronized(Restaurant.freeTables){
            Restaurant.freeTables.add(tableAssigned);
            Restaurant.freeTables.notify();
        }
    }
    
    //If the last diner leaves, print the time
    public void lastDinerLeaves(){
        synchronized(Restaurant.dinerCompletedCount){
            Restaurant.dinerCompletedCount++;
            
            if(Restaurant.dinerCompletedCount == Restaurant.dinerCount){
                System.out.println(getTime() + " - The last Diner leaves the restaurant.");
                System.exit(0);
            }
        }
    }
}
