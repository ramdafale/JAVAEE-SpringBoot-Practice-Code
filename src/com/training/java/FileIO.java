package com.training.java;

import java.io.File;

//*********************************************************************** 
//	Program name : 			FIleIO
//	Program Description :	Reading a file from local disk and making changes in file.
// 							reflect those changes in new file.
//***********************************************************************

// importing required library file 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class FileIO {

    public static void main(String[] args) {

    	
    	
        /**********************************************************************************
         * Description :It uses class File to create a new file and uses a FileInputStream
         * 				to read a byte from hello file till end of file and print the content
         * 				into console   	 
         * ********************************************************************************/
    	
        File file = new File("./hello.txt");
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

           
            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }
            
            
            

            /**********************************************************************************
             * Description :It uses class File to create a new file and uses a FileOutputStream
             * 				to write a content.
             * 				 
             * ********************************************************************************/
            
            
            File data = new File("./hello.txt");  
            FileOutputStream file2 = new FileOutputStream(data);  
            FilterOutputStream filter = new FilterOutputStream(file2);  
            String s="Welcome to java.";      
            byte b[]=s.getBytes();      
            filter.write(b);     
            filter.flush();  
            filter.close();  
            file2.close();  
            System.out.println("Success...");  
            
            
            

        } catch (IOException e) {
            e.printStackTrace(); // It tells Specified reason of exception.
            
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
      
    }
}