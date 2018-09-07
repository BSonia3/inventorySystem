/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.service;

import com.mycompany.inventorysystem.dao.ItemPersistenceException;
import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface ItemServiceLayer {
    
    void createItem(Item item)throws ItemPersistenceException,ItemDataValidationException;
    
    List<Item> getAllItems() throws ItemPersistenceException;
    
    Item getItem(String name) throws ItemPersistenceException;
    
    Item  removeItem(String name) throws ItemPersistenceException;
    
    Item updateBuyItem(String name,int qtty)throws ItemPersistenceException;
    
    Item updateSellItem(String name,int qtty)throws ItemPersistenceException;
    
    Item updateSellPrice(String name ,BigDecimal price) throws ItemPersistenceException;
    
    List<Item> getSoldItems() throws ItemPersistenceException; 
    
    List<Item> getDeletedItems() throws ItemPersistenceException;
   
    List<Item> getOldPricesItems() throws ItemPersistenceException;
    
    BigDecimal calculateCostValue(Item item) throws ItemPersistenceException;
    void updateItemsReport() throws ItemPersistenceException;
    
}
