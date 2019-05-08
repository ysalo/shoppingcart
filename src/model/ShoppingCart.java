/*
 * TCSS 305 - Autumn 2016
 * Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.Map;


/**
 * Stores information about the customer's overall purchase.
 * 
 * @author Yaro Salo
 * @version October 14 2016
 */
public class ShoppingCart {
    
    /** An instance of the BigDecimal class to reduce typing. */
    private static final BigDecimal ZERO_DECIMAL = new BigDecimal("0.0");
    
    /** Boolean value to indicate if a customer is a member. */
    private boolean myIsMember;
    
    /** A map to store customer orders, Items as keys and ItemOrders as values. */
    private final Map<Item, ItemOrder> myCart;
    
    /**
     * Creates an empty shopping cart.
     */
    public ShoppingCart() {
       
        //no membership by default.
        myIsMember = false;
        myCart = new HashMap<Item, ItemOrder>();
    }

    /**
     * Adds an order to the shopping cart, replacing any previous order 
     * for an equivalent item with the new order.
     * 
     * @param theOrder is an ItemOrder which should be added to the ShoppingCart.
     * 
     */
    public void add(final ItemOrder theOrder) {
        
        myCart.put(theOrder.getItem(), theOrder);
    }

    /**
     * Sets whether or not the customer for this shopping cart has a store membership.
     * 
     * @param theMembership boolean value to indicate if the customer has a membership
     * true if yes, false otherwise.
     * 
     */
    public void setMembership(final boolean theMembership) {
       
        myIsMember = theMembership;
    }

    /**
     * Returns the total cost of this shopping cart rounded to two 
     * decimal places according to ROUND_HALF_EVEN rule.
     * 
     * @return the total for this shopping cart, rounded to two decimal places.
     * 
     */
    public BigDecimal calculateTotal() {
        
        BigDecimal result = ZERO_DECIMAL; 
        
        if (this.myIsMember) { 
            //If the customer is a member call the member function and reassign result.
            result = this.memberTotal();
        } else {
            //Otherwise call the non member function and reassign result.
            result = this.nonMemberTotal();
        }
        
        return result;
    }
    /**
     * Private helper method which calculates the total of a cart if the 
     * customer is not a member.
     * 
     * @return a BigDecimal value with a scale of 2 and rounded according to 
     * the ROUND_HALF_EVEN rule.
     * 
     */
    private BigDecimal nonMemberTotal() {
        
        BigDecimal result = ZERO_DECIMAL;
        
        for (final ItemOrder order: myCart.values()) {
            //Grab the order quantity.
            final int orderQuantity = order.getQuantity();
            //Grab the single price of the item.
            final BigDecimal singlePrice = order.getItem().getPrice();
            //Store the result in a temporary variable to reduce clutter.
            final BigDecimal tempResult = singlePrice.multiply(new BigDecimal(orderQuantity));
            result = result.add(tempResult); 
            
        }
        
        return result.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * Private helper method which calculates the total of a cart if the 
     * customer is a member.
     * 
     * @return a BigDecimal value with a scale of 2 and rounded according to 
     * the half even rule.
     * 
     */
    private  BigDecimal memberTotal() {
        BigDecimal result = ZERO_DECIMAL;
        
        for (final ItemOrder order: myCart.values()) {
            
            final int orderQuantity = order.getQuantity();
            final BigDecimal singlePrice = order.getItem().getPrice();
            
            //Check if the item is a bulk item.
            if (order.getItem().isBulk()) {
                //Grab bulk quantity and price.
                final int bulkQuantity = order.getItem().getBulkQuantity();
                final BigDecimal bulkPrice = order.getItem().getBulkPrice();
                
                //Calculate the amount of times a bulk discount can be applied to an order.
                final int applyDiscount = (int) orderQuantity / bulkQuantity;
                
                //Get the number of the item which could not be applied to the bulk discount.
                final int leftOver = orderQuantity % bulkQuantity;
                
                //Calculate the discounted and not discounted amounts.
                final BigDecimal discounted =
                                bulkPrice.multiply(new BigDecimal(applyDiscount));
               
                final BigDecimal notDiscounted = 
                                singlePrice.multiply(new BigDecimal(leftOver));
                
                //Add the values to the results.
                result = result.add(discounted);
                result = result.add(notDiscounted);    
            
                //If the item is not bulk use it's single price.
            } else {
                final BigDecimal tempResult = 
                                singlePrice.multiply(new BigDecimal(orderQuantity));
                result = result.add(tempResult); 
                
            }
            
        }
        
        return result.setScale(2, RoundingMode.HALF_EVEN);
    }

    
    /**
     * Removes all orders from the cart. 
     * 
     */
    public void clear() {
        myCart.clear();
    }
    
    /**
     * Returns a String representation of a ShoppingCart. The String will be formated 
     * as follows: "ItemOrder1 \n ItemOrder2 \n..." without quotes.
     * 
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        
        for (final ItemOrder order: myCart.values()) {
            sb.append(order);
            sb.append('\n');
              
        }
        return sb.toString();
    }

   
}
