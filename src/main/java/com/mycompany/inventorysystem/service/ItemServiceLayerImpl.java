/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.service;

import com.mycompany.inventorysystem.dao.ItemAuditDao;
import com.mycompany.inventorysystem.dao.ItemDao;
import com.mycompany.inventorysystem.dao.ItemPersistenceException;
import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sonia
 */
public class ItemServiceLayerImpl implements ItemServiceLayer{

    private ItemAuditDao auditDao;
    ItemDao dao;
   
   public ItemServiceLayerImpl(ItemDao dao,ItemAuditDao auditDao ) {
    this.dao = dao;
    this.auditDao = auditDao;
      }
    @Override
    public void createItem(Item item) throws ItemPersistenceException, ItemDataValidationException {
        validateItemData(item);
        dao.createItem(item.getItemName(), item);
    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String name) throws ItemPersistenceException {
       return dao.getItem(name);
    }

    @Override
    public Item removeItem(String name) throws ItemPersistenceException {
       Item item = dao.removeItem(name);
       return item;
    }

    @Override
    public Item updateBuyItem(String name,int qtty) throws ItemPersistenceException {
        return dao.updateBuyItem(name, qtty);
    }

    @Override
    public Item updateSellItem(String name,int qtty) throws ItemPersistenceException {
       return dao.updateSellItem(name, qtty);
    }

   
   @Override 
    public BigDecimal calculateCostValue(Item item) throws ItemPersistenceException{
    
    BigDecimal costVal = item.getCostPrice().multiply(new BigDecimal(item.getQuantity()));
    return costVal;
    }
   
    

    @Override
    public Item updateSellPrice(String name ,BigDecimal price) throws ItemPersistenceException {
      return dao.updateSellPrice(name, price);
    }
    
    private void validateItemData(Item item) throws
        ItemDataValidationException {

    if (item.getItemName() == null
            || item.getItemName().trim().length() == 0
            || item.getCostPrice() == null
            || item.getCostPrice().equals(0) 
            || item.getSellingPrice() == null
            || item.getSellingPrice().equals(0)
            
            )
            {

        throw new ItemDataValidationException(
                "ERROR: All fields [ItemName, CostPrice, SellingPrice] are required.");
    }
    
}

    @Override
    public List<Item> getSoldItems() throws ItemPersistenceException {
       return dao.getSoldItems();
    }
    @Override
    public List<Item> getDeletedItems() throws ItemPersistenceException{
      return  dao.getDeletedItems();
    }
    
    @Override
    public List<Item> getOldPricesItems() throws ItemPersistenceException {
       return dao.getOldPricesItems();
    }
    

    @Override
    public void updateItemsReport() throws ItemPersistenceException {
     dao.updateItemsReport();
    }



}
