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
public class Order {
    
    volatile int burgers;
    volatile int fries;
    volatile int coke;
    
    //Constructor for orders
    public Order(int burgers, int fries, int coke) {
        
        this.burgers = burgers <= 0 ? 0 : burgers;
        this.fries = fries <= 0 ? 0 : fries;
        this.coke = coke <= 0 ? 0 : coke;
    }
    
    
}
