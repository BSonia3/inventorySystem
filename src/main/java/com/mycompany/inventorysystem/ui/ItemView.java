/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.ui;

import com.mycompany.inventorysystem.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import static oracle.jrockit.jfr.events.Bits.floatValue;



/**
 *
 * @author sonia
 */
public class ItemView {
    
    // dependendy injection
    private UserIO io;
    public ItemView(UserIO io){
        this.io =io;
    }
            
    
    public int printMenuAndGetSelection() {
        io.print("\nMain Menu");
        io.print("1. Create An Item");
        io.print("2. Remove an Item");
        io.print("3. updateBuy Item");
        io.print("4. updateSell for an Item");
        io.print("5. report");
        io.print("6. updateSellPrice for an Item");
        io.print("7. Exit");

        io.print("\nPlease select from the above choices : 1, 7\n");
        return io.readInt("1,7");
    }
   
     public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}

    
    
    //implement CreateItem
    public Item getNewItemInfo() {
        io.print("Please enter Item Name");
        String itemName = io.readString("Please enter Item Name");
        io.print("Please enter Item CostPrice");
        BigDecimal itemCost = io.readBigDecimal("Please enter Item CostPrice");
        
        io.print("Please enter Item SelingPrice");
        BigDecimal itemSelling = io.readBigDecimal("Please enter Item SellingPrice");
        
        //io.print("Please enter Item Quantity");
        //int quantity = io.readInt("Please enter Item Quantity");
        Item currentItem = new Item(itemName);
        currentItem.setCostPrice(itemCost);
        currentItem.setSellingPrice(itemSelling);
        //currentItem.setQuantity(quantity);

        return currentItem;
    }

    public void displayCreateItemBanner() {
        io.print("\n=== CREATE ITEM ===");
    }

    public void displayCreateSuccessBanner() {
        io.print("\nItem successfully added.  Please hit enter to continue\n");
        io.readString("Please hit enter to continue.");
    }

    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void exitMessage() {
      io.print("\n .....Good Bye ... See you soon .....!!!");
      
     
    }

    public void displayRemoveItemBanner() {
         io.print("\n=== REMOVE ITEM ===");
    }

    public String getItemName() {
        io.print("\nPlease enter The Item Name : ");
        return io.readString("\nPlease enter The Item to remove : \n");
        
    }

    public void displayRemoveSuccessBanner() {
        io.print("\nItem successfully Deleted.  Please hit enter to continue\n");
        io.readString("Please hit enter to continue."); }

    public void displayUpdateItemBanner() {
       io.print("\n=== UPDATE ITEM ===");
    }

    public int getItemQuantity() {
       io.print("\nPlease enter quantity Of the Item : ");
       return io.readInt("\nPlease enter quantity Of the Item : ");
    }

    public void displayUpdateSuccessBanner() {
       io.print("\n Item successfully Updated.  Please hit enter to continue\n");
        io.readString("Please hit enter to continue."); 
    }

    public BigDecimal getNewPrice() {
       io.print("\nPlease enter a new Price for the Item : ");
       return io.readBigDecimal("\nPlease enter a new Price for the Item : ");
  }

    public void displayDisplayAllBanner() {
     io.print("Item Name     Bought At       Sold At       AvlableQty     Value");
     io.print("---------     ---------       --------      ---------     ---------");
   
    }

  // getAll Items to the screen
    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemName() + "          "
                    + currentItem.getCostPrice() + "          "+currentItem.getSellingPrice() + "          "
                    + currentItem.getQuantity()+ "          "+currentItem.getCostPrice().multiply(new BigDecimal(currentItem.getQuantity())));
        }
    io.print("--------------------------------------------------------------------------------------------");
    }
    
  
    
    public BigDecimal displayTotalValue( List<Item> itemList){
       
        float totalValue =0;
        for (Item currentItem : itemList) {
        
       totalValue +=floatValue((currentItem.getCostPrice().multiply(new BigDecimal(currentItem.getQuantity()))));
        
        } 
        BigDecimal totalBuyValue =  new BigDecimal(totalValue).setScale(2, RoundingMode.HALF_UP); 
      io.print("Total value                                                   "+totalBuyValue);
       return totalBuyValue;
    } 

    
    
   public void displayProfit(List<Item>itemListSold,List<Item> deletedItemList,List<Item> oldPricesList){
     float soldValue =0;
        
        for (Item currentItem : itemListSold) {    
       soldValue +=floatValue((currentItem.getSellingPrice().subtract(currentItem.getCostPrice())).multiply(new BigDecimal(currentItem.getQuantity())));
        } 
          
         BigDecimal profitValue =  new BigDecimal(soldValue).setScale(2, RoundingMode.HALF_UP); 
        BigDecimal totalprofitValue =(profitValue.add(displayOldPrice(oldPricesList))).subtract((displayPriceDeletedItems(deletedItemList))) ;
        io.print("Profit since previous report                                " +totalprofitValue);
      
               } 
   

    public BigDecimal displayPriceDeletedItems(List<Item> deletedItemList) {
         float costDeletedItems = 0;
         
        for (Item currentItem : deletedItemList) {
        
      costDeletedItems +=floatValue(((currentItem.getCostPrice().multiply(new BigDecimal(currentItem.getQuantity())))));
       
        }
        
         BigDecimal deletedValue =  new BigDecimal(costDeletedItems).setScale(2, RoundingMode.HALF_UP);
         return deletedValue;
    }

    public BigDecimal displayOldPrice(List<Item> oldPricesList) {
        float oldCost= 0;
        for(Item item:oldPricesList){
            oldCost=floatValue(item.getCostPrice().multiply(new BigDecimal(item.getQuantity())));
         }
       BigDecimal oldSellValue = new BigDecimal(oldCost).setScale(2, RoundingMode.HALF_UP);
       return oldSellValue; 
    }
   
    
}
