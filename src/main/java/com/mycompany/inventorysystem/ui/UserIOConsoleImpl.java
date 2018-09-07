/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventorysystem.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author sonia
 */
public class UserIOConsoleImpl implements UserIO{
    
        Scanner scanner = new Scanner(System.in);

    
    
    @Override
     public int readInt(String prompt){
         int value=0;
         prompt =scanner.nextLine();
         value= Integer.parseInt(prompt);
         return value;
     }
     
    @Override
     public int readInt(String prompt, int min, int max){
         
         min=0; max=100000;
         int value;
      
        // System.out.println("Prompt the user to enter a number from the menu : ");
         value=readInt(prompt);
         if (value>=min && value<=max){
         //System.out.println("Thank you this is a correct answer ");
      
         return value;
         }
         
         else
         {
           
       //  System.out.println(" enter a value from the menu : ");  
         readInt(prompt,min,max);
        
         }
     
         return value;
        
         }
         
         
    @Override
    public  String readString(String prompt){
      prompt= scanner.nextLine();
      //System.out.println( prompt ); 
      return prompt;
    }
    
    
    @Override
    public float readFloat(String prompt){
      float value;
         prompt =scanner.nextLine();
         value= Float.parseFloat(prompt);
         return value;  
    }

    @Override
    public float readFloat(String prompt, float min, float max){
        
      min=0; max=10000;
      float value;
      //System.out.println("Prompt the user to enter a number from the menu : ");
         prompt =scanner.nextLine();
         value= readFloat(prompt);
         if (value<=max && value>=min){
         return value;
         }
         else {
             
        // System.out.println("try again; this value is not between min and max ");  
         readFloat(prompt,min,max);
         return value;
         } 
         
         
    
    }
    
    
    @Override
    public double readDouble(String prompt){
      float value;
         prompt =scanner.nextLine();
         value= Float.parseFloat(prompt);
         return value;  
    }

    @Override
    public double readDouble(String prompt,double min, double max){
        
      min=0; max=10000;
      double value;
      //System.out.println("Prompt the user to enter a number from the menu  : ");
         prompt =scanner.nextLine();
         value= readDouble(prompt);
         if (value<=max && value>=min){
         return value;
         }
         else {
             
        // System.out.println("try again; this value from the menu  ");  
         readDouble(prompt,min,max);
         return value;
         } 
         
      }
    
     @Override
    public long readLong(String prompt){
      long value;
         prompt =scanner.nextLine();
         value= Long.parseLong(prompt);
         return value;  
    }

    @Override
    public long readLong(String prompt,long min, long max){
        
      min=0; max=10000;
      long value;
     // System.out.println("Prompt the user to enter a number from the menu  : ");
         prompt =scanner.nextLine();
         value= readLong(prompt);
         if (value<=max && value>=min){
         return value;
         }
         else {
             
         //System.out.println("try again; this value is not from the menu  ");  
         readDouble(prompt,min,max);
         return value;
         } 
         
      }
    
    @Override
    public void print(String prompt){
        System.out.println( prompt );
       } 

   
    @Override
    public BigDecimal readBigDecimal(String prompt){
     
         prompt =scanner.nextLine();
         BigDecimal value = new BigDecimal(prompt);
         return value;  
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int min, int max){
        
      min=0; max=10000;
      int value=0;
      BigDecimal min1 = new BigDecimal(min);
      BigDecimal max1 = new BigDecimal(max);
      BigDecimal value1 = new BigDecimal(value);
      //System.out.println("Prompt the user to enter a number from the menu : ");
         prompt =scanner.nextLine();
         value1= readBigDecimal(prompt);
         if (value<=max && value>=min){
         return value1;
         }
         else {
             
        // System.out.println("try again; this value is not between min and max ");  
         readBigDecimal(prompt,min,max);
         return value1;
         } 
    }
    
   
    
    
}
