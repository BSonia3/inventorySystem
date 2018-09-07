/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.service;

import com.mycompany.inventorysystem.dao.ItemPersistenceException;
import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class ItemServiceLayerTest {
    
    private ItemServiceLayer service;
    
    public ItemServiceLayerTest() {
         ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", ItemServiceLayer.class);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createItem method, of class ItemServiceLayer.
     */
    @Test
    public void testCreateItem() throws Exception {
        
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
       
        service.createItem(item);
        
        assertEquals(1,service.getAllItems().size());
    }

    /**
     * Test of getAllItems method, of class ItemServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
          assertEquals(1,service.getAllItems().size());
    }

    /**
     * Test of getItem method, of class ItemServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
       
        service.createItem(item);
        service.getItem(item.getItemName());
        assertNotNull(item.getItemName());
        Item item3=service.getItem("Med");
        assertNull(item3);
        
    }

    /**
     * Test of removeItem method, of class ItemServiceLayer.
     */
    @Test
    public void testRemoveItem() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
       
        service.createItem(item);
        service.removeItem(item.getItemName());
        assertNotNull(item.getItemName());
        Item item3=service.removeItem("Med");
        assertNull(item3);
    }

    /**
     * Test of updateBuyItem method, of class ItemServiceLayer.
     */
    @Test
    public void testUpdateBuyItem() throws Exception {
        
        BigDecimal cost = new BigDecimal(10).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(15).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Med01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
        service.createItem(item);
        service.updateBuyItem(item.getItemName(), 30);
       // assertEquals(0,item.getQuantity());
        assertEquals(30,service.updateBuyItem(item.getItemName(), 30).getQuantity());
    }

    /**
     * Test of updateSellItem method, of class ItemServiceLayer.
     */
    @Test
    public void testUpdateSellItem() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
        item.setQuantity(35);
        service.createItem(item);
        
        service.updateSellItem(item.getItemName(),3);
        
        assertEquals(32,35-(service.updateSellItem(item.getItemName(),3).getQuantity()));
    }

    /**
     * Test of updateSellPrice method, of class ItemServiceLayer.
     */
    @Test
    public void testUpdateSellPrice() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
        item.setQuantity(10);
       
        service.createItem(item);
        Item newItem = service.updateSellPrice(item.getItemName(), new BigDecimal(2.5));
        assertEquals(new BigDecimal(2.5),newItem.getSellingPrice());
        
    }

    /**
     * Test of getSoldItems method, of class ItemServiceLayer.
     */
    @Test
    public void testGetSoldItems() throws Exception {
         assertEquals(1,service.getAllItems().size());
        
    }

    /**
     * Test of getDeletedItems method, of class ItemServiceLayer.
     */
    @Test
    public void testGetDeletedItems() throws Exception {
         assertEquals(1,service.getAllItems().size());
    }

    /**
     * Test of getOldPricesItems method, of class ItemServiceLayer.
     */
    @Test
    public void testGetOldPricesItems() throws Exception {
         assertEquals(1,service.getAllItems().size());
    }

    /**
     * Test of calculateCostValue method, of class ItemServiceLayer.
     */
    @Test
    public void testCalculateCostValue() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
        item.setQuantity(10);
        //add item to dao
        service.createItem(item);
        service.calculateCostValue(item);
        assertEquals(new BigDecimal(30).setScale(2, RoundingMode.HALF_UP),service.calculateCostValue(item));
    }

    /**
     * Test of updateItemsReport method, of class ItemServiceLayer.
     */
    @Test
    public void testUpdateItemsReport() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
        item.setQuantity(10);
        //add item to dao
        service.createItem(item);
        service.updateItemsReport();
        assertNotNull(item);
       
    }

}