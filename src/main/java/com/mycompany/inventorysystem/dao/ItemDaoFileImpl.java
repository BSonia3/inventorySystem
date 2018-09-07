/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

import com.mycompany.inventorysystem.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author sonia
 */
public class ItemDaoFileImpl implements ItemDao {

    @Override
    public Item createItem(String name, Item item) throws ItemPersistenceException {
     
      loadItems();
      Item newItem = items.put(name,item);
      writeItem();
      return newItem;

    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
       
        loadItems(); 
        List<Item> myItems=new ArrayList<>(items.values());
        //Sort the list in alphabet order
        Collections.sort(myItems, (Item a1, Item a2) -> a1.getItemName().compareTo(a2.getItemName()) );

        return myItems;
    }
       

    @Override
    public Item getItem(String name) throws ItemPersistenceException {
       loadItems();
       Item item = items.get(name);
       return item;
    }

    @Override
    public Item removeItem(String name) throws ItemPersistenceException {
      loadItems(); 
      Item item =items.remove(name);
      //loadDeletedItem();
      // keep track of the deleted items in file reportItem.txt
      itemsDeleted.put(name, item);
      writeDeletedItem();
      writeItem();
      return item;
      
    }

    @Override
    public Item updateBuyItem(String name ,int qtty) throws ItemPersistenceException {
       List<Item> listItem =getAllItems();
        if(listItem==null){
        listItem =new ArrayList<>() ;
        }
       Item item = items.get(name);
       item.setQuantity(qtty);
       items.put(name, item);
       writeItem();
       
       return item; 
      
    }

    @Override
    public Item updateSellItem(String name ,int qtty) throws ItemPersistenceException {
        List<Item> listItem =getAllItems();
        if(listItem==null){
        listItem =new ArrayList<>() ;
        }
       Item item = items.get(name);
       Item itemSold = items.get(name);
       if(item!= null){
       item.setQuantity(item.getQuantity()-qtty);
       }
        
       items.put(name, item);
       writeItem();
       
       // keep trck of the the sold qtty in file qttyItem.txt
        loadQttytItem();
        Item myItem=getItemSold(name);
        if(myItem == null){
        itemSold.setQuantity(qtty);
        itemsQtty.put(name,itemSold );
        writeQttyItem();
        }else
        {
         myItem.setQuantity(qtty+myItem.getQuantity());
         itemsQtty.put(name,myItem );
         writeQttyItem();
        }
        
       // writeQttyItem();
        
        return item; 
    }
    
   

    @Override
    public Item updateSellPrice(String name ,BigDecimal price) throws ItemPersistenceException {
      loadItems();
      Item item = items.get(name);
      
       // keep tack of the old prices 
      List<Item> oldItems=getSoldItems();
      Item oldItem = itemsQtty.get(name);
      if(item.getItemName().equals(oldItem.getItemName())){
      itemsOldPrice.put(name,oldItem);
      loadItemOldPrice();
      writeOldPrice();
      oldItem.setQuantity(0);
      oldItem.setSellingPrice(price);
      writeQttyItem();
      }
      
      //change the price
      item.setSellingPrice(price);
      writeItem();
      
      return item;
    }
    
    
    @Override
    public List<Item> getDeletedItems() throws ItemPersistenceException {
       
        //loadDeletedItem(); 
        //sortMap(items);
        return new ArrayList<>(itemsDeleted.values());   
    }
    
     @Override
    public Item getRemovedItem(String name) throws ItemPersistenceException {
       loadDeletedItem();
       Item item = itemsDeleted.get(name);
       return item;
    }
    
    
    
     @Override
    public Item getItemSold(String name) throws ItemPersistenceException {
       loadQttytItem();
       Item item = itemsQtty.get(name);
       return item;
    }
    
    @Override
    public List<Item> getSoldItems() throws ItemPersistenceException {
        loadQttytItem();
        
        return new ArrayList<>(itemsQtty.values());   
    }
    
    @Override
    public List<Item> getOldPricesItems() throws ItemPersistenceException {
       loadItemOldPrice();
       return new ArrayList<>(itemsOldPrice.values());   
    }
    
    
    
    
    // add our items file
    public static final String STORE_FILE = "Item.txt";
    public static final String DELIMITER = " :: ";
    
    private Map<String,Item> items = new HashMap<>();
   
    
    // read from a file
    private void loadItems() throws ItemPersistenceException {
	   
            Scanner scanner;
	    
	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(STORE_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new ItemPersistenceException(
	                "-_- Could not load data into memory!", e);
	    }
	    // currentLine holds the most recent line read from the file
	    String currentLine;
	    
	    String[] currentTokens;
	    // Go through STORE_FILE line by line, decoding each line into a 
	    // Item object.
	    // Process while we have more lines in the file
            
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // break up the line into tokens
	        currentTokens = currentLine.split(DELIMITER);
	         
	        Item currentItem = new Item(currentTokens[0]);
                currentItem.setCostPrice(new BigDecimal(currentTokens[1]));
                currentItem.setSellingPrice(new BigDecimal(currentTokens[2]));
                currentItem.setQuantity(Integer.parseInt(currentTokens[3]));
                
	        // Put currentItem into the map using name item as the key
	        items.put(currentItem.getItemName(), currentItem);
                
	    }
	    // close scanner
	    scanner.close();
            
	}
    
    
    
    
    
    /**    write from memory to file
	 
	 * 
	 * @throws ItemPersistenceException if an error occurs writing to the file
	 */
	private void writeItem() throws ItemPersistenceException {
	   
	    PrintWriter out;
	    
	    try {
             
	      out = new PrintWriter(new FileWriter(STORE_FILE));
	    } catch (IOException e) {
	        throw new ItemPersistenceException(
	                "Could not save ITEM data.", e);
	      }
	    
	    // Write out the item objects to the STORE file.
	    
	   // List<Item> itemList = this.getAllItems();
	   // for (Item currentItem : itemList) {
               
	        // write the item object to the file
	     loadItems();
            
      items.forEach((name,currentItem)-> out.println(currentItem.getItemName() + DELIMITER
	                + currentItem .getCostPrice().setScale(2, RoundingMode.HALF_UP)+ DELIMITER 
                        + currentItem .getSellingPrice().setScale(2, RoundingMode.HALF_UP)+ DELIMITER 
	                + currentItem .getQuantity() ));
	            
	        // force PrintWriter to write line to the file
	        out.flush();
                
       // }
	    // Clean up
	    out.close();
	}
  
        
        
 /// removed Item file  
    public static final String REPORT_FILE = "deletedItem.txt";
    
    private Map<String,Item> itemsDeleted = new HashMap<>();
   
        
    private void loadDeletedItem() throws ItemPersistenceException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(REPORT_FILE)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
                currentItem.setCostPrice(new BigDecimal(currentTokens[1]));
                currentItem.setSellingPrice(new BigDecimal(currentTokens[2]));
                currentItem.setQuantity(Integer.parseInt(currentTokens[3]));
              

           
            itemsDeleted.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

  
    private void writeDeletedItem() throws ItemPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(REPORT_FILE));
        } catch (IOException e) {
            throw new ItemPersistenceException(
                    "Could not save item data.", e);
        }
        
        loadDeletedItem();
            
      itemsDeleted.forEach((name,currentItem)->out.println(currentItem.getItemName() + DELIMITER
                    + currentItem .getCostPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getSellingPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getQuantity()));
                      
           // force PrintWriter to write line to the file
            out.flush();
       // }
        // Clean up
        out.close();
    }
    
    
    ///// for quantity sold 
     
    public static final String QUANTITY_FILE = "quantitytItem.txt";
    
    private Map<String,Item> itemsQtty = new HashMap<>();
   
        
    private void loadQttytItem() throws ItemPersistenceException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(QUANTITY_FILE)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
                currentItem.setCostPrice(new BigDecimal(currentTokens[1]));
                currentItem.setSellingPrice(new BigDecimal(currentTokens[2]));
                currentItem.setQuantity(Integer.parseInt(currentTokens[3]));
              

           
            itemsQtty.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

  
    private void writeQttyItem() throws ItemPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(QUANTITY_FILE));
        } catch (IOException e) {
            throw new ItemPersistenceException(
                    "Could not save item data.", e);
        }
        
        loadQttytItem();
            
      itemsQtty.forEach((name,currentItem)->out.println(currentItem.getItemName() + DELIMITER
                    + currentItem .getCostPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getSellingPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getQuantity()));
                        
           // force PrintWriter to write line to the file
            out.flush();
       // }
        // Clean up
        out.close();
    }
    
    
    
    ///// To keep update prices in the file 
     
    public static final String PRICES_FILE = "oldPriceItem.txt";
    
    private Map<String,Item> itemsOldPrice = new HashMap<>();
   
        
    private void loadItemOldPrice() throws ItemPersistenceException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(new FileReader(PRICES_FILE)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException(
                    "-_- Could not load data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
                currentItem.setCostPrice(new BigDecimal(currentTokens[1]));
                currentItem.setSellingPrice(new BigDecimal(currentTokens[2]));
                currentItem.setQuantity(Integer.parseInt(currentTokens[3]));
              

           
            itemsOldPrice.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

  
    private void writeOldPrice() throws ItemPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRICES_FILE));
        } catch (IOException e) {
            throw new ItemPersistenceException(
                    "Could not save item data.", e);
        }
        
      loadItemOldPrice();
           
      itemsOldPrice.forEach((o,currentItem)->out.println(currentItem.getItemName() + DELIMITER
                    + currentItem .getCostPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getSellingPrice().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem .getQuantity()));
                        
           // force PrintWriter to write line to the file
            out.flush();
       // }
        // Clean up
        out.close();
    }
    
    
    @Override
    public void updateItemsReport() throws ItemPersistenceException{
       List<Item> deletedItemList= getDeletedItems();
        for (Item currentItem : deletedItemList) {
             currentItem.setQuantity(0);
             itemsDeleted.put(currentItem.getItemName(),currentItem);
             writeDeletedItem();
             
           }
        List<Item>itemListSold = getSoldItems();
        for (Item currentItem : itemListSold) {
             currentItem.setQuantity(0);
             itemsQtty.put(currentItem.getItemName(),currentItem );
             writeQttyItem();
           }
        
       List<Item>itemOldPriceList =  getOldPricesItems();
       for (Item currentItem : itemOldPriceList) {
             currentItem.setQuantity(0);
             itemsOldPrice.put(currentItem.getItemName(),currentItem );
             writeOldPrice();
       }
    }
    
    
}
