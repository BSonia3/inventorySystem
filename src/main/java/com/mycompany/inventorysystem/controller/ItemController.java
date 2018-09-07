/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.controller;

import com.mycompany.inventorysystem.dao.ItemPersistenceException;
import com.mycompany.inventorysystem.dto.Item;
import com.mycompany.inventorysystem.service.ItemDataValidationException;
import com.mycompany.inventorysystem.service.ItemServiceLayer;
import com.mycompany.inventorysystem.ui.ItemView;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author sonia
 */
public class ItemController {
    
     //for dependacy injection
    ItemView view;
    private ItemServiceLayer service;
   
   public ItemController(ItemServiceLayer service, ItemView view) {
    this.service = service;
    this.view = view;
    }
   
    public void run()   {
	        boolean keepGoing = true;
	        int menuSelection = 0;
	       try {
                   while (keepGoing) {
                    
                    
                        menuSelection = getMenuSelection();
                        
                        switch (menuSelection) {
                           
                            case 1:
                                 createItem();
                                break;
                            case 2:
                                removeItem();
                                break;
                            case 3:
                                updateBuyItem();
                                break;
                            case 4:
                                updateSellItem();
                                break;
                            case 5:
                                giveReport();
                                break;
                            case 6:
                                updateSellPrice();
                                break;
                            case 7:
                                
                                keepGoing = false;
                                break;
                            default:
                                unknownCommand();
                        }
                  

	        }
	        exitMessage();
                  } catch (ItemPersistenceException  ex) {
                      view.displayErrorMessage(ex.getMessage());
                  }
	    }
       // call the method from view to print the menu chooses
        
      private int getMenuSelection() {
         return view.printMenuAndGetSelection();
        }

      
    private void createItem() throws ItemPersistenceException{
       
        view.displayCreateItemBanner();
        boolean hasErrors = false;
       do{
        Item newItem = view.getNewItemInfo();
        try { 
        service.createItem(newItem);
        view.displayCreateSuccessBanner();
        hasErrors = false;
       }catch (ItemDataValidationException ex) {
        hasErrors= true;        
        view.displayErrorMessage(ex.getMessage());
        }        
       } while(hasErrors);
    }
    
  
    
    private void removeItem() throws ItemPersistenceException {
       view.displayRemoveItemBanner();
       String ItemName= view.getItemName();
       service.removeItem(ItemName);
       view.displayRemoveSuccessBanner();
    }
    

    private void updateBuyItem() throws ItemPersistenceException {
     view.displayUpdateItemBanner();
     String ItemName= view.getItemName();
     int quantity= view.getItemQuantity();
     service.updateBuyItem(ItemName, quantity);
     view.displayUpdateSuccessBanner();
     
      
    }

    private void updateSellItem() throws ItemPersistenceException {
     view.displayUpdateItemBanner();
     String ItemName= view.getItemName();
     int quantity= view.getItemQuantity();
     service.updateSellItem(ItemName, quantity);
     view.displayUpdateSuccessBanner();
    }

    private void giveReport() throws ItemPersistenceException {
    view.displayDisplayAllBanner(); 
    List<Item> itemList = service.getAllItems();
    List<Item> soldItemList = service.getSoldItems();
    List<Item> deletedItemList = service.getDeletedItems();
    List<Item> oldPricesList = service.getOldPricesItems();
    view.displayItemList(itemList);    
    
        itemList.stream()
        .forEach(o -> {
        try {
            service.calculateCostValue(o);
           
        } catch (ItemPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());}
        });
        
        view.displayTotalValue(itemList);
        view.displayPriceDeletedItems(deletedItemList);
        view.displayProfit(soldItemList,deletedItemList,oldPricesList);
        view.displayOldPrice(oldPricesList);
        service.updateItemsReport();
     
    }

    private void updateSellPrice() throws ItemPersistenceException {
     view.displayUpdateItemBanner();
     String ItemName= view.getItemName();
     BigDecimal newPrice= view.getNewPrice();
     service.updateSellPrice(ItemName, newPrice);
     view.displayUpdateSuccessBanner();
    }

    private void unknownCommand() {
       view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
      view.exitMessage();  
    }
  
    
}
