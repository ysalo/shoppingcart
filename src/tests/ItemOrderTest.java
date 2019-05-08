/*
 * TCSS 305 - Autumn 2016
 * Assignment 2 - Shopping Cart
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import model.Item;
import model.ItemOrder;

import org.junit.Before;
import org.junit.Test;




/**
 * Tests the ItemOrder class.
 * 
 * @author Yaro Salo
 * @version October 14 2016
 */
public class ItemOrderTest {
    
    /** ItemOrder initialized before every test. */
    private ItemOrder myItemOrder;
    
    /** Item initialized before every test. */
    private Item myItem;
    
    /**
     * A method to initialize the test fixtures before each test.
     */
    @Before
    public void setUp() {
       
        myItem = new Item("'Java Rules!' button", new BigDecimal("0.95"), 10, 
                          new BigDecimal("5.00"));
        
        myItemOrder = new ItemOrder(myItem , 15);          
    }

    /**
     * Test the constructor with a null value.
     */
    @Test(expected = NullPointerException.class)
    public void testItemOrderNullItem() {
        new ItemOrder(null, 15);
    }
    
    /**
     * Test the constructor with an illegal quantity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemOrderNegativeQuantity() {
        new ItemOrder(myItem, -1);
    }

    /**
     * Test the getItem() method.
     */
    @Test
    public void testGetItem() {
        assertTrue(myItemOrder.getItem().equals(myItem));
    }
    
    /**
     * Test the getQuantity() method.
     */
    @Test
    public void testGetQuantity() {
        assertEquals(myItemOrder.getQuantity(), 15);
    }
    
    /**
     * Test the toString() method.
     */
    @Test
    public void testToString() {
        assertEquals("'Java Rules!' button, $0.95 (10 for $5.00), 15 ordered.", 
                     myItemOrder.toString());
        
    }

}
