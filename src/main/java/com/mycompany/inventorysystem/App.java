/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem;

import com.mycompany.inventorysystem.controller.ItemController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class App {
    public static void main(String[] args) {
        
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
       ItemController controller = ctx.getBean("controller", ItemController.class);
        controller.run();
    }
    
    
}
