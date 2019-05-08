/*
 * TCSS 305 - Autumn 2016
 * Assignment 2 - Shopping Cart
 */

package model;

import java.util.Objects;

/**
 * Stores information about a purchase order for a particular item:
 * a reference to the item itself and the quantity desired.
 * 
 * @author Yaro Salo
 * @version October 14 2016
 * 
 */
public final class ItemOrder {
    
    /** A reference to an item desired. */
    private final Item myItem;
    
    /** The quantity of an item desired. */
    private final int myQuantity;
    
    
    /**
     * Constructs and item order for the given quantity of the given item.
     * 
     * @param theItem is the given Item object.
     * @param theQuantity is the quantity of an Item desired as an integer.
     * 
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
       
        myItem = Objects.requireNonNull(theItem);
        
        if (theQuantity < 0) {
            throw new IllegalArgumentException("The quantity must be positive."); 
        }
        myQuantity = theQuantity; 
    }

    /**
     * Returns a reference to an item.
     * 
     * @return a reference to the Item.
     */
    public Item getItem() {
        
        return myItem;
    }
    
    /**
     * Returns the quantity for this ItemOrder.
     * 
     * @return  the quantity as an integer.
     */
    public int getQuantity() {
        
        return myQuantity;
    }

    /**
     * Returns a String representation of this ItemOrder. The String will be formated 
     * as follows: "Item, quantity" without the quotes.
     * 
     * @return String representation of an ItemOrder.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(myItem.toString());
        sb.append(", ");
        sb.append(myQuantity);
        sb.append(" ordered.");
        
        return sb.toString();
    }
    
}
