/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sonia
 */
public class ItemDaoStubImpl implements ItemDao {
    
    Item myItem;
    List<Item>itemList = new ArrayList();
    
    // to initialise
    public ItemDaoStubImpl(){
        myItem = new Item("Book01");
        myItem.setCostPrice(new BigDecimal(5));
        myItem.setSellingPrice(new BigDecimal(7));
        myItem.setQuantity(20);
        
        itemList.add(myItem);
    }

    @Override
    public Item createItem(String name, Item item) throws ItemPersistenceException {
        if(name.equals(myItem.getItemName())){
            return myItem;
        }else {
            return null;
        }
     }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
       return itemList; 
    }

    @Override
    public Item getItem(String name) throws ItemPersistenceException {
        if(name.equals(myItem.getItemName())){
            return myItem;
        }else {
            return null;
        } }

    @Override
    public Item removeItem(String name) throws ItemPersistenceException {
       if(name.equals(myItem.getItemName())){
            return myItem;
        }else {
            return null;
        }
    }
    

    @Override
    public Item updateBuyItem(String name, int qtty) throws ItemPersistenceException {
        Item item  = new Item(name);
       
           item.setQuantity(qtty);
           return item;
      
        }
    @Override
    public Item updateSellItem(String name, int qtty) throws ItemPersistenceException {
        Item item =  new Item(name);
        item.setQuantity(qtty);
        return item;
    }

    @Override
    public Item updateSellPrice(String name, BigDecimal price) throws ItemPersistenceException {
         Item item  = new Item(name);
         item.setSellingPrice(price);
           return item;
    }

    @Override
    public List<Item> getDeletedItems() throws ItemPersistenceException {
       return itemList ;
    }

    @Override
    public Item getRemovedItem(String name) throws ItemPersistenceException {
        if(name.equals(myItem.getItemName())){
            return myItem;
        }else {
            return null;
        }}

    @Override
    public Item getItemSold(String name) throws ItemPersistenceException {
       if(name.equals(myItem.getItemName())){
            return myItem;
        }else {
            return null;
        } }

    @Override
    public List<Item> getSoldItems() throws ItemPersistenceException {
       return itemList ;
    }

    @Override
    public void updateItemsReport() throws ItemPersistenceException {
    myItem.setQuantity(0);
    }

    @Override
    public List<Item> getOldPricesItems() throws ItemPersistenceException {
        return itemList ;
    }
    
    
}
