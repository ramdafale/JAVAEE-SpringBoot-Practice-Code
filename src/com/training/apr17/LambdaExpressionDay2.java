
/*******************************************************************************************************************
 * Program Name: 		Lambda Expression 
 * Program Description : Write a lambda expression for finding a palindrome of following
 * 						1->IsOdd
 * 						2->IsPrime
 * 						3->IsPalindrome
 * 
 *Programmer Name: 		Ram Dafale
 *Release Date:			17-April-2018
 ********************************************************************************************************************/

package com.tarining.apr17;

/*******************************************************************************************************************
 * Class Name: LambdaExp Program Description : This class include the all
 * Functional interface required. : Declaration of all Functional interfaces.
 * 
 * Programmer Name: Ram Dafale Release Date: 17-April-2018
 ********************************************************************************************************************/

public class LambdaExp {

	// Functional Interface
	interface IsOdd {
		public boolean isOdd(int number);
	}

	// Functional Interface
	interface IsPrime {
		public boolean isPrime(int number);
	}

	// Functional Interface
	interface isPalindrome {
		public boolean isPalindrome(int number, int flag);
	}

	/*******************************************************************************************************************
	 * Method Name: LambdaExp Program Description : Main method is consists of 3
	 * different logic for 3 activities of the program. 1. IsOdd method take 1
	 * parameter and check weather it is odd or not. 2. IsPrime method take 1
	 * parameter and check weather it is prime or not. 3. isPalindrome method take 1
	 * parameter and check weather it is palindrome or not.
	 ********************************************************************************************************************/

	public static void main(String[] args) {
		// Taking input
		int input = 121;

		// lambda expression to check if a number is even/odd.

		/*******************************************************************************************************************
		 * Method Name: IsOdd() Program Description : 1. IsOdd method take 1 parameter
		 * and check weather it is odd or not.
		 ********************************************************************************************************************/
		IsOdd check1 = (number) -> {
			if (input % 2 == 0)
				return false;
			else
				return true;
		};
		System.out.println("No is odd : " + check1.isOdd(input));

		/*******************************************************************************************************************
		 * Method Name: IsPrime() Program Description : 2. IsPrime() method take 1
		 * parameter and check weather it is prime or not.
		 ********************************************************************************************************************/

		IsPrime prime1 = (number) -> {
			for (int i = 2; i <= input / 2; i++) {
				if (input % i == 0)
					return false;
			}
			return true;
		};
		System.out.println("No is prime : " + prime1.isPrime(input));

		/*******************************************************************************************************************
		 * Method Name: isPalindrome() Program Description : 3. isPalindrome() method
		 * take 1 parameter and check weather it is palindrome or not.
		 ********************************************************************************************************************/

		// lambda expression to check if a number is palindrome.

		isPalindrome palindrome1 = (number, flag) -> {
			int no1 = number;
			int remainder = 0, sum = 0;
			while (number > 0) {
				remainder = number % 10;
				number = number / 10;
				flag = flag * 10 + remainder;
				sum = flag;
			}
			System.out.println(sum);
			if (sum == no1)
				return true;
			else
				return false;

		};
		int flag = 0;
		System.out.println("No is palindrome : " + palindrome1.isPalindrome(input, flag));
	}

}

/********************************************
 * Output: 
 * No is odd : true
No is prime : false
121
No is palindrome : true

 */



