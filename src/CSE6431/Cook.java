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
public class Cook extends Utils implements Runnable {

    int CookId;
    Diner dinerServed;

    public Cook() {

    }

    @Override
    public void run() {
        while (true) {
            try {
                //Cook gets the order to be prepared
                getOrder();
            } catch (InterruptedException ex) {
                System.out.println("Error while the cook is getting the order " + ex.getMessage());
            }

            try {
                //Cook prepares the order
                handleOrder();
            } catch (InterruptedException ex) {
                System.out.println("Error while the cook is preparing the order " + ex.getMessage());
            }
        }
    }

    //Get the order from a hungry diner and prepare the order
    void getOrder() throws InterruptedException {
        synchronized (Restaurant.hungryDiners) {
            while (Restaurant.hungryDiners.isEmpty()) {
                Restaurant.hungryDiners.wait();
            }

            dinerServed = Restaurant.hungryDiners.remove(0);
            System.out.println(getTime() + " - Cook " + CookId + " processes Diner " + dinerServed.dinerId + "'s order.");
        }
    }

    //Prepare the order: Burger Machine - 5 mins, Fries Machine - 3 mins, Coke Machine - 1 min
    void handleOrder() throws InterruptedException {
        Order order = dinerServed.order;
        int itemCount = order.burgers + order.fries + order.coke;
        while (itemCount > 0) {
            
        //Prepare burgers
        if(order.burgers > 0 && Restaurant.isBurgerFree == true){
                synchronized(Restaurant.burgerMachine){
                    while(order.burgers > 0){
                        Restaurant.isBurgerFree = false;
                        System.out.println(getTime() + " - Cook " + CookId + " uses the burger machine.");
                        Thread.sleep(500);
                        order.burgers--;
                        if(order.burgers == 0){     
                            Restaurant.isBurgerFree = true;
                            Restaurant.burgerMachine.notify();
                        }
                    } 
                }
            }
            
            //Prepare fries
            if(order.fries > 0 && Restaurant.isFriesFree == true){
                synchronized(Restaurant.friesMachine){
                    while(order.fries > 0){
                        Restaurant.isFriesFree = false;
                        System.out.println(getTime() + " - Cook " + CookId + " uses the fries machine.");
                        Thread.sleep(300);
                        order.fries--;
                        if(order.fries == 0){
                            Restaurant.isFriesFree = true;
                            Restaurant.friesMachine.notify();
                        }
                    } 
                }
            }
            
            //Prepare coke
            if(order.coke > 0 && Restaurant.isCokeFree == true){
                synchronized(Restaurant.cokeMachine){
                    while(order.coke > 0){
                        Restaurant.isCokeFree = false;
                        System.out.println(getTime() + " - Cook " + CookId + " uses the coke machine.");
                        Thread.sleep(100);
                        order.coke--;
                        if(order.coke == 0){
                            Restaurant.isCokeFree = true;
                            Restaurant.cokeMachine.notify();
                        }
                    }
                }
            }
               
            itemCount = order.burgers + order.fries + order.coke;

            if (itemCount == 0) {
                synchronized (dinerServed) {
                    dinerServed.notify();
                }
            }
        }
    }

}
