/*
 * TCSS 305 - Autumn 2016
 * Assignment 2 - Shopping Cart
 */

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import model.Item;

import org.junit.Before;
import org.junit.Test;



/**
 * Tests the Item class.
 * 
 * @author Yaro Salo
 * @version October 14 2016
 *
 */
public class ItemTest {
    
    /** An instance of a non bulk item used in test. */
    private Item myNonBulkItem;
    
    /** An instance of a bulk item used in test. */
    private Item myBulkItem;
    
    /** A valid price that will be used in my tests. */
    private BigDecimal myTestP;
    
    /** A valid name to be used in my test. */
    private String myTestName;
    
    /** A valid quantity to be used in my tests. */
    private int myTestQ;
    
    /**
     * A method to initialize the test fixtures before each test.
     */
    @Before
    public void setUp() {
        myNonBulkItem = new Item("Rubik's Cube" , new BigDecimal("9.99"));
        myBulkItem = new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                              new BigDecimal("10.04"));
        myTestP = new BigDecimal("0.0");
        myTestName = "Item";
        myTestQ = 0;
        
    }
    
    /** 
     * Test the non bulk constructor with legal parameters. 
     */
    @Test
    public void testNonBulkConstructor() {
        new Item(myTestName, myTestP);

    }
    
    /** 
     * Test the non bulk constructor with a null name. 
     */
    @Test(expected = NullPointerException.class)
    public void testNonBulkNullName() {
        new Item(null, myTestP);
    }
    
    /** 
     * Test the non bulk constructor with a null price. 
     */
    @Test(expected = NullPointerException.class)
    public void testNonBulkNullPrice() {
        new Item(myTestName, null);
        
    }
    
    /** 
     * Test the non bulk constructor with a negative price. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNonBulkNegativePrice() {
        new Item(myTestName, new BigDecimal("-10"));
    }
    
    /**
     * Test the non bulk constructor default values.
     */
    @Test 
    public void testNonBulkDefault() {
        assertEquals(myNonBulkItem.getBulkPrice(), new BigDecimal("9.99"));
        assertEquals(myNonBulkItem.getBulkQuantity(), 1);
    }
    
    /** 
     * Test the bulk constructor with legal parameters. 
     */
    @Test
    public void testBulkConstructor() { 
        new Item(myTestName, myTestP, myTestQ, myTestP);
    }
    
    /** 
     * Test the bulk constructor with a null name. 
     */
    @Test(expected = NullPointerException.class)
    public void testBulkNullName() {
        new Item(null, myTestP, myTestQ, myTestP);
        
    }  
    
    /** 
     * Test the bulk constructor with a null price. 
     */
    @Test(expected = NullPointerException.class)
    public void testBulkNullPrice() {
        new Item(myTestName, null, myTestQ, myTestP);   
    }
    
    /** 
     * Test the bulk constructor with a negative single price. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkConstructorPrice() {
        new Item(myTestName, new BigDecimal("-1.0"), myTestQ, myTestP); 
    }
   
    /** 
     * Test the bulk constructor with a negative quantity. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkQuantity() {
        new Item(myTestName, myTestP, -1, myTestP);   
    }
    
    /** 
     * Test the bulk constructor with a null bulk price. 
     */
    @Test(expected = NullPointerException.class)
    public void testNullBulkPrice() {
        new Item(myTestName, myTestP, myTestQ, null);
    }
    
    /** 
     * Test the bulk constructor with a negative bulk price. 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeBulkPrice() {
        new Item(myTestName, myTestP, myTestQ, new BigDecimal("-1.0"));
    }
    
    
    /**
     * Test the getPrice() method.
     */
    @Test
    public void testGetPriceTrue() {
        //Instead of assertEquals() because .equals() does not compare BigDecimals for 
        //equality if the scale is different.
        assertTrue(myNonBulkItem.getPrice().compareTo(new BigDecimal("9.9900")) == 0);     
   
    }
   
    
    /** 
     * Test the isBulk() method with a bulk item.
     */
    @Test 
    public void testIsBulkTrue() {
        assertTrue(myBulkItem.isBulk());
          
    }
    
    /** 
     * Test the isBulk() method with non bulk item.
     */
    @Test 
    public void testIsBulkFalse() {
        assertFalse(myNonBulkItem.isBulk());
    }
    
    /**
     * Test the toString() method with a non bulk item.
     */
    @Test 
    public void testNonBulkItemToString() {
        assertEquals("Rubik's Cube, $9.99", myNonBulkItem.toString());
    }
    
    /**
     * Test the toString() method with a bulk item.
     */
    @Test 
    public void testBulkItemToString() {
        assertEquals("Silly Putty, $4.41 (6 for $10.04)", myBulkItem.toString());
    }
        
    /** 
     * Test the equals() method for reflexivity.
     */
    @Test 
    public void testEqualsIsReflexive() { 
        assertEquals(myBulkItem, myBulkItem); 
        
    }

    
    /**
     * Test equals() method for symmetry.
     */
    @Test 
    public void testEqualsIsSymmetric() {
        assertEquals(myBulkItem, new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                          new BigDecimal("10.04"))); 
        assertEquals(new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                          new BigDecimal("10.04")), myBulkItem);
        
    }
    
    
    /**
     * Test equals() for transitivity.
     */
    @Test 
    public void testEqualsIsTransitiveBulk() {
        
        final Item otherItem = new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                              new BigDecimal("10.04"));
        
        assertEquals(myBulkItem, new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                          new BigDecimal("10.04")));
        assertEquals(new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                              new BigDecimal("10.04")), otherItem);
        assertEquals(myBulkItem, otherItem);
        
    }
       
    /** 
     * Test equals() for consistency.
     */
    @Test 
    public void testEqualsIsConsistentBulk() {
        assertEquals(myBulkItem, new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                          new BigDecimal("10.04")));
        assertEquals(myBulkItem, new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                          new BigDecimal("10.04")));
    }
    
    /**
     * Test equals() for null.
     */
    @Test 
    public void testEqualsNull() {
        assertNotEquals(myBulkItem, null);
        
    }
 
    /**
     * Test the equals() method with non bulk item. 
     */
    @Test
    public void testEqualsNonBulk() {
        final Item otherItem = new Item("Rubik's Cube" , new BigDecimal("9.99"));
        
        assertEquals(myNonBulkItem, otherItem);
        
    }
    
    /**
     * Test equals() different name.
     */
    @Test 
    public void testNotEqualsDifferentName() {
        final Item otherItem = new Item("Serious Putty" , new BigDecimal("4.41"), 6,
                                        new BigDecimal("10.04"));
        assertNotEquals(myBulkItem, otherItem);
    }
    
    /**
     * Test equals() different single price.
     */
    @Test 
    public void testNotEqualsDifferentSinglePrice() {
        final Item otherItem = new Item("Silly Putty" , new BigDecimal("4.42"), 6,
                                        new BigDecimal("10.04"));
        assertNotEquals(myBulkItem, otherItem);
    }
    
    /**
     * Test equals() different bulk quantity.
     */
    @Test 
    public void testNotEqualsDifferentQuantity() {
        final Item otherItem = new Item("Silly Putty" , new BigDecimal("4.41"), 5,
                                        new BigDecimal("10.04"));
        assertNotEquals(myBulkItem, otherItem);
    }
    
    /**
     * Test equals() different bulk price.
     */
    @Test 
    public void testNotEqualsDifferentBulkPrice() {
        final Item otherItem = new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                        new BigDecimal("10.05"));
        assertNotEquals(myBulkItem, otherItem);
    }
    
    /**
     * Test hashCode bulk.
     */
    @Test 
    public void testHashCodeBulk() {
        final Item otherBulkItem = new Item("Silly Putty" , new BigDecimal("4.41"), 6,
                                            new BigDecimal("10.04"));
        //Same items should have the same hash code.
        assertEquals(myBulkItem.hashCode(), otherBulkItem.hashCode());
    }
    
    /**
     * Test the hashCode() non bulk.
     */
    @Test
    public void testHashCodeNonBulk() { 
        
        final Item otherNonBulkItem = new Item("Rubik's Cube" , new BigDecimal("9.99"));
        assertEquals(myNonBulkItem.hashCode(), otherNonBulkItem.hashCode());
         
    }
}
