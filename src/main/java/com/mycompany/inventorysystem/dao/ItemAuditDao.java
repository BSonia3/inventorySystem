/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

/**
 *
 * @author sonia
 */
public interface ItemAuditDao {
   public void writeAuditEntry(String entry) throws ItemPersistenceException; 
       
}
