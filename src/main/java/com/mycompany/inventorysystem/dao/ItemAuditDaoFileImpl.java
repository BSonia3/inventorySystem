/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author sonia
 */
public class ItemAuditDaoFileImpl implements ItemAuditDao {
public static final String AUDIT_FILE = "audit.txt";
    PrintWriter out;
    
    @Override
    public void writeAuditEntry(String entry) throws ItemPersistenceException {
       try {
           out = new PrintWriter (new FileWriter(AUDIT_FILE, true));
       
       }catch (IOException e){
           throw new ItemPersistenceException(" Could not persist audit information ", e);
           
       }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush(); }
    
}
