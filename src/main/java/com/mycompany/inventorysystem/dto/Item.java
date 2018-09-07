/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author sonia
 */
public class Item {
   
   private String itemName ;
   private BigDecimal costPrice ;
   private BigDecimal sellingPrice ;
   private int quantity ;

   public Item(String item) {
        this.itemName = item;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.itemName);
        hash = 43 * hash + Objects.hashCode(this.costPrice);
        hash = 43 * hash + Objects.hashCode(this.sellingPrice);
        hash = 43 * hash + this.quantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.costPrice, other.costPrice)) {
            return false;
        }
        if (!Objects.equals(this.sellingPrice, other.sellingPrice)) {
            return false;
        }
        return true;
    }

   @Override
        public String toString() {
        return " ItemName: " + itemName + " |CostPrice: " + costPrice + " |sellingPrice: " 
                
            +sellingPrice + " |Quantity: " + quantity;
        }
   

    
}
