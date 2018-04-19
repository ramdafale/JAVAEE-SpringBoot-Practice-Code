package com.training.java;

/************************************************************************************
 * Program Name: Contains Duplicate
 * Description : In a word if there is 2 or more than 2 occurance of letter then output 
 * 				 must be true or else false.
 * Programmer Name: Ram Dafale
 * DateOfRelease: 16-April-2018
 ***********************************************************************************/

public class ContainsDuplicate {

	 public static void main(String[] args) {
	        String s = "abcdefga";
	        System.out.println(s + " " + check(s)); //Calling ther method 
	        s = "abcdefgh";
	        System.out.println(s + " " + check(s)); //Calling ther method 
	        s = "abcdefdh";
	        System.out.println(s + " " + check(s)); //Calling ther metho
	    }
   
	 
	 
	 /************************************************************************************
	  *Method Name: check
	  *Description: it compare character by character in a given string and if occurance of
	  *				letter is found then return boolean true else false.	
	  ***********************************************************************************/	 
	 
	    public static boolean check(String s) {
	        for (int i = 0; i < (s.length() - 1); i++) {
	            for (int j = i + 1; j < s.length(); j++) {
	                if (s.charAt(i) == s.charAt(j)) {
	                    return true;     // It means 
	                }
	            }
	        }
	        return false;
	    }
		
		
	}
/**********
Output:
	abcdefga true
	abcdefgh false
	abcdefdh true
**********/


