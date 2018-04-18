
/*
 * Program Name: Contains Duplicate
 * Description : In a word if there is 2 or more than 2 occurrence 
 * 				 of letter then output 
 * 				 must be true or else false.
 * Programmer Name: Ram Dafale
 * DateOfRelease: 16-April-2018
 */
package palindromepmd;

import java.util.logging.Logger;


public class ContainsDuplicate {

	 public static void main(String args[])  {
	        String inputString = "abcdefga";
	        
	        final  	Logger printf = Logger.getLogger(ContainsDuplicate.class.getName());
	        
	        printf.info(inputString + " " + check(inputString)); //Calling the method 
	        inputString = "abcdefgh";
	        printf.info(inputString + " " + check(inputString)); //Calling the method 
	        inputString = "abcdefdh";
	        printf.info(inputString + " " + check(inputString)); //Calling the method 
	    }

	 
	 
	 /*
	  *Method Name: check
	  *Description: it compare character by character in a given string and if occurance of
	  *				letter is found then return boolean true else false.	
	  */	 
	 
	   public static boolean check(String inputString2) {
	        for (int i = 0; i < (inputString2.length() - 1); i++) {
	            for (int j = i + 1; j < inputString2.length(); j++) {
	                if (inputString2.charAt(i) == inputString2.charAt(j)) {
	                    return true;     // 
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
