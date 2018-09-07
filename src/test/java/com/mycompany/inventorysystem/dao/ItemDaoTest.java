/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

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
public class ItemDaoTest {
    
    private ItemDao dao ;//= new ItemDaoFileImpl(); //without using spring
    
    public ItemDaoTest() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       dao = ctx.getBean("ItemDao",ItemDao.class);   
   }
    
  @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    // set the dao into a known good state 
    public void setUp() throws Exception  {
        // list all the items 
        List<Item>itemList = dao.getAllItems();
        // remove all items to start our tests
        
        for(Item item : itemList) {
           dao.removeItem(item.getItemName());
        }
           
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createItem method, of class ClassRosterDao.
     */
    @Test
    public void testAddGetItem() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
       
        //add item to dao
        dao.createItem(item.getItemName(),item);
        
        //ask dao for item tab01
        Item fromDao = dao.getItem(item.getItemName());
       // assertNotNull(item);
       // assertNotNull(fromDao);
        assertEquals(item,fromDao);
   
    }
    

    /**
     * Test of getAllItems method
     */
    @Test
    public void testGetAllItems() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        
    }
    

    /**
     * Test of removeItem method, of class ItemDao.
     */
    @Test
    public void testRemoveItem() throws Exception {
        
         Item item1 =  new Item("Med");
         item1.setCostPrice(new BigDecimal(20));
         item1.setSellingPrice(new BigDecimal(25));
         //item1.setQuantity(90);
        
         dao.createItem(item1.getItemName(), item1);
        
         
         Item item2 =  new Item("Phone");
         item2.setCostPrice(new BigDecimal(100));
         item2.setSellingPrice(new BigDecimal(200));
         //item1.setQuantity(90);
        
         dao.createItem(item2.getItemName(), item2);
        
      
         
        dao.removeItem(item1.getItemName());
        assertEquals(1,dao.getAllItems().size());
        assertNull(dao.getItem(item1.getItemName()));
        
         dao.removeItem(item2.getItemName());
         assertNull(dao.getItem(item2.getItemName()));
         assertEquals(0,dao.getAllItems().size());
         
      
   
    }

    /**
     * Test of updateBuyItem method, of class ItemDao.
     */
    @Test
    public void testUpdateBuyItem() throws Exception {
         Item item1 =  new Item("Med");
         item1.setCostPrice(new BigDecimal(20));
         item1.setSellingPrice(new BigDecimal(25));
         
         dao.createItem(item1.getItemName(), item1);
         dao.getAllItems();
        
         
         assertEquals(90,(dao.updateBuyItem(item1.getItemName(),90)).getQuantity()) ;
    }

    /**
     * Test of updateSellItem method, of class ItemDao.
     */
    @Test
    public void testUpdateSellItem() throws Exception {
         Item item1 =  new Item("Med");
         item1.setCostPrice(new BigDecimal(20));
         item1.setSellingPrice(new BigDecimal(25));
         item1.setQuantity(90);
         dao.createItem(item1.getItemName(), item1);
         
         
         assertEquals(88,(dao.updateSellItem(item1.getItemName(),2)).getQuantity()) ; 
    }

    /**
     * Test of updateSellPrice method, of class ItemDao.
     */
    @Test
    public void testUpdateSellPrice() throws Exception {
         Item item1 =  new Item("Med");
         item1.setCostPrice(new BigDecimal(20));
         item1.setSellingPrice(new BigDecimal(25));
         item1.setQuantity(90); 
         dao.createItem(item1.getItemName(), item1);
         Item newItem = dao.updateSellPrice(item1.getItemName(),new BigDecimal(23));
       
         assertEquals(new BigDecimal(23),newItem.getSellingPrice());
        
        
    }

    /**
     * Test of getDeletedItems method, of class ItemDao.
     */
    @Test
    public void testGetDeletedItems() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        
    }

    /**
     * Test of getRemovedItem method, of class ItemDao.
     */
    @Test
    public void testGetRemovedItem() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        
    }

    /**
     * Test of getItemSold method, of class ItemDao.
     */
    @Test
    public void testGetItemSold() throws Exception {
        BigDecimal cost = new BigDecimal(3).setScale(2, RoundingMode.HALF_UP);
        BigDecimal price = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
       
        Item item = new Item("Tab01");
        item.setCostPrice(cost);
        item.setSellingPrice(price);
       
        //add item to dao
        dao.createItem(item.getItemName(),item);
        
        //ask dao for item tab01
        Item fromDao = dao.getItem(item.getItemName());
       // assertNotNull(item);
       // assertNotNull(fromDao);
        assertEquals(item,fromDao);
    }

    /**
     * Test of getSoldItems method, of class ItemDao.
     */
    @Test
    public void testGetSoldItems() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        
    }

    /**
     * Test of updateItemsReport method, of class ItemDao.
     */
    @Test
    public void testUpdateItemsReport() throws Exception {
        
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        dao.updateItemsReport();
        assertEquals(0,dao.getItem(item1.getItemName()).getQuantity());
        assertEquals(0,dao.getItem(item2.getItemName()).getQuantity());
    }

    /**
     * Test of getOldPricesItems method, of class ItemDao.
     */
    @Test
    public void testGetOldPricesItems() throws Exception {
        BigDecimal cost = new BigDecimal(3);
        BigDecimal price = new BigDecimal(5);
        // add two items to dao
        Item item1 = new Item("Tab01");
        item1.setCostPrice(cost);
        item1.setSellingPrice(price);
        //item1.setQuantity(90);
        dao.createItem(item1.getItemName(),item1);
        assertNotNull(item1);
        
        BigDecimal cost1 = new BigDecimal(1);
        BigDecimal price1 = new BigDecimal(2.5);
        Item item2 = new Item("Food01");
        item2.setCostPrice(cost1);
        item2.setSellingPrice(price1);
        //item2.setQuantity(30);
        dao.createItem(item2.getItemName(),item2);
        assertNotNull(item2);
        assertEquals(2,dao.getAllItems().size());
        
        
    }

    
    
}
