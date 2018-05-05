
// Commenting like java docs 

/*Program Name: 		Lambda Expression 
 * Program Description : Write a lambda expression for finding a
 * 						 palindrome of following IsOdd,IsPrime,IsPalindrome
 *Programmer Name: 		Ram Dafale
 *Release Date:			17-April-2018
*/

package palindromepmd;

/*
 * Class Name: LambdaExp Program Description : This class include the all
 * Functional interface required. : Declaration of all Functional interfaces.
 * 
 * Programmer Name: Ram Dafale Release Date: 17-April-2018
 */
import java.util.logging.Logger;

public class LambdaExp {

	@FunctionalInterface
	interface IsOdd {
		public boolean isOdd(int number);
	}

	@FunctionalInterface
	interface IsPrime {
		public boolean isPrime(int number);
	}

	@FunctionalInterface
	interface isPalindrome {
		public boolean isPalindrome(int number, int flag);
	}

	/*
	 * Method Name: LambdaExp Program Description : Main method is consists of 3
	 * different logic for 3 activities of the program. 1. IsOdd method take 1
	 * parameter and check weather it is odd or not. 2. IsPrime method take 1
	 * parameter and check weather it is prime or not. 3. isPalindrome method take 1
	 * parameter and check weather it is palindrome or not.
	 */

	// removed unnecessary String args from main
	public static void main() {

		final Logger printf = Logger.getLogger(LambdaExp.class.getName());

		// Taking input
		// declaring local variable final because it is not going to change

		final int input = 121;

		// lambda expression to check if a number is even/odd.

		/*
		 * Method Name: IsOdd() Program Description : 1. IsOdd method take 1 parameter
		 * and check weather it is odd or not.
		 */
		IsOdd check1 = (number) -> {
			if (input % 2 == 0)
				return false;
			else
				return true;
		};
		printf.info("No is odd : " + check1.isOdd(input));

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
		printf.info("No is prime : " + prime1.isPrime(input));

		/*
		 * Method Name: isPalindrome() Program Description : 3. isPalindrome() method
		 * take 1 parameter and check weather it is palindrome or not.
		 */

		isPalindrome palindrome1 = (number, flag) -> {
			// write a every new variable into new line
			int no1 = number;
			int remainder = 0;
			int sum = 0;
			while (number > 0) {
				remainder = number % 10;
				number = number / 10;
				flag = flag * 10 + remainder;
				sum = flag;
			}
			// Using Logger
			printf.info(sum);
			if (sum == no1)
				return true;
			else
				return false;

		};
		int flag = 0;

		printf.info("No is palindrome : " + palindrome1.isPalindrome(input, flag));

	}

}
