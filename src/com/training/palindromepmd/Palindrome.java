package palindromepmd;

/*
 *  Program name :		  Check if integer is palindrome or not.
 *  Program Description : It check if given number is read same from 
 *  					  left and right. 
 *   					  eg. 121, 131;
 * Programmer Name: Ram Dafale
 * DateOfRelease: 16-April-2018
 */



//Library to take input from user in console
import java.util.Scanner;
import java.util.logging.Logger;

// class

public class Palindrome    {

	
	/*
	 *Method Name: main
	 *Description: It takes input from user. then it breaks the number
	 *				 by getting remainder 
	 *				then it adds that remainder to sum varibale. and then divide the number.
	 *				this process repeats till we get same number as user specified.
	 */	 

	public static void main(String args[]) {
		final	  Scanner inputNumber = new Scanner(System.in); 
	 final  	Logger printf = Logger.getLogger(Palindrome.class.getName());
		 int num;
		 int temp;
		 int addition;
		 addition= 0;
		 int firstResult;
		 // sum variable to 
		 // try block to monitor exception and to avoid the wrong input from user
		 
		 
	
		 printf.info("Enter any number");
	        num = inputNumber.nextInt();
		 
		
		 // take input from user and store in a variable 
	      
	       
	       // temporary variable to store user input 
	        temp = num;
	       // loop for checking the number greater than 0
	        while(num <= 0)
	        {
	        	firstResult = num % 10; 
	        	addition = addition * 10 + firstResult;
	            num = num / 10;
	        }
	        // comparing given integer with calculated integer
	        // if sum and temp are equal then given number is palindrome
	        if(addition == temp)
	        {
	        	printf.info("Given number "+temp+" is Palindrome");
	        }
	        else
	        {
	        	printf.info("Given number "+temp+" is Not Palindrome");
	        }
	        inputNumber.close();
		 }

	
		 
		 // catch block to catch exception if any and show appropriate response to user
		
		
		
	      

}


/*
output:
Enter any number:131
Given number 131 is Palindrome
*/

