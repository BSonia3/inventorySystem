/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface ItemDao {
    Item createItem(String name, Item item)throws ItemPersistenceException;
    
    List<Item> getAllItems() throws ItemPersistenceException;
    
    Item getItem(String name) throws ItemPersistenceException;
    
    Item  removeItem(String name) throws ItemPersistenceException;
    
    Item updateBuyItem(String name ,int qtty )throws ItemPersistenceException;
    
    Item updateSellItem(String name ,int qtty )throws ItemPersistenceException;
   
    Item updateSellPrice(String name ,BigDecimal price) throws ItemPersistenceException;
    List<Item>getDeletedItems() throws ItemPersistenceException;
    Item getRemovedItem(String name) throws ItemPersistenceException;
    Item getItemSold(String name) throws ItemPersistenceException;
    List<Item> getSoldItems() throws ItemPersistenceException;
    void updateItemsReport() throws ItemPersistenceException;
    List<Item> getOldPricesItems() throws ItemPersistenceException;
}
