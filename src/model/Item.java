/*
 * TCSS 305 - Autumn 2016
 * Assignment 2 - Shopping Cart
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Stores information about an individual item.
 * 
 * @author Yaro Salo
 * @version October 14 2016
 *
 */

public final class Item {
    
    /**
     * A number formatter which formats a number to U.S. currency.
     */
    public static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
    
    /** A BigDecimal object equal to zero. */
    private static final BigDecimal ZERO_DECIMAL = new BigDecimal("0.0");
    
    /** Default bulk quantity to assign to an item if it does not have one. */
    private static final int DEFAULT_BULK_QUANTITY = 1;
        
    /** Name of the item. */
    private final String myName;
    
    /** The price of the item. */
    private final BigDecimal myPrice;
    
    /** The bulk quantity of the item, if an item has one. */
    private final int myBulkQuantity;
    
    /** The bulk price of the item, if an item has one. */
    private final BigDecimal myBulkPrice;
    
    /**
     * Constructs a representation of an item based on the name and price.
     * The default bulk quantity and bulk price are 1 and the single price 
     * respectively.
     *
     * @param theName is the name of the item to be constructed as a String.
     * @param thePrice is the price of the item as a BigDecimal.
     * 
     */
    public Item(final String theName, final BigDecimal thePrice) {
        
        this(theName, thePrice, DEFAULT_BULK_QUANTITY, thePrice);
            
    }

    /**
     * Constructs a representation of an item based on the name, single price
     * bulk quantity and bulk price.
     *
     * @param theName is the name of the item to be constructed as a String.
     * @param thePrice is the price of the item as a BigDecimal.
     * @param theBulkQuantity is the minimum quantity required for an item to be considered 
     * being purchased in bulk.
     * @param theBulkPrice is the discounted price for an item which is being bought in bulk 
     * as a BigDecimal.
     * 
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {   
        
        //Make sure that the name is non null.
        myName = Objects.requireNonNull(theName);
        
        /*
         * Validate parameters. Single price and bulk price can't be null or negative. 
         * Bulk quantity can't be negative.
         */
       
        Objects.requireNonNull(thePrice);
        Objects.requireNonNull(theBulkPrice);
        
        /*
         * I choose to put these all in one condition because they're testing
         * essentially the same test also removes the CheckStyle warning and looks cleaner. 
         */
        if (ZERO_DECIMAL.compareTo(thePrice) > 0 || ZERO_DECIMAL.compareTo(theBulkPrice) > 0
                        || theBulkQuantity < 0) {
            throw new IllegalArgumentException("The value must be non negative.");
        }
        
        myPrice = thePrice;
        
        myBulkQuantity = theBulkQuantity;
       
        myBulkPrice = theBulkPrice;  
    }

    /**
     * Returns the single item price for this item.
     * 
     * @return the single item price as BigDecimal.
     * 
     */
    public BigDecimal getPrice() {
        
        return myPrice;
    }
    
    /**
     * Returns the bulk quantity for this item.
     * 
     * @return the bulk quantity as an integer.
     * 
     */
    public int getBulkQuantity() {
        
        return myBulkQuantity;
    }
    
    /**
     * Returns the bulk price for this item.
     * 
     * @return the bulk price as a BigDecimal.
     * 
     */
    public BigDecimal getBulkPrice() {
        
        return myBulkPrice;
    }

    /**
     * Returns true if the Item has a bulk pricing and false otherwise.
     * 
     * @return true if an item has a bulk pricing and false otherwise.
     * 
     */
    public boolean isBulk() {
                
        // If the item has a bulk quantity greater than 1 then it's bulk.   
        return this.getBulkQuantity() > 1;
    }
    
    /**
     * Returns a a string representation of an Item. The String will be returned in the format:
     * name followed by the price separated by a comma. "name, price" without quotes. If the 
     * Item has a bulk quantity the format will be "name, price (bulk quantity for bulk price)"
     * without quotes.
     * 
     * @return String representation of an Item.
     * 
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder();
        sb.append(myName);
        sb.append(", ");
        sb.append(FORMATTER.format(myPrice));
        
        if (this.isBulk()) {
            sb.append(" (");
            sb.append(myBulkQuantity);
            sb.append(" for ");
            sb.append(FORMATTER.format(myBulkPrice));
            sb.append(')');   
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * Returns true if the specified object is equivalent to this Item, and false 
     * otherwise. Two items are equivalent if they have exactly equivalent names, prices, 
     * bulk quantities and bulk prices.
     * 
     * @return true if objects are equal and false otherwise.
     * 
     */
    @Override
    public boolean equals(final Object theOther) {
        
        boolean returnValue = false;
        
        if (this == theOther) {
            returnValue = true;
        
        } else if (theOther != null && this.getClass() == theOther.getClass()) {
            
            final Item otherItem = (Item) theOther;
           
            returnValue = Objects.equals(myName, otherItem.myName)
                       && Objects.equals(myPrice, otherItem.myPrice)
                       && Objects.equals(myBulkQuantity, otherItem.myBulkQuantity) 
                       && Objects.equals(myBulkPrice, otherItem.myBulkPrice);
        } 
        
        return returnValue;
    }

    /**
     * {@inheritDoc}
     * 
     * Returns and integer hash code for an item.
     * 
     * @return hash code as an integer.
     * 
     */
    @Override
    public int hashCode() {
        
        return Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
    }

}
