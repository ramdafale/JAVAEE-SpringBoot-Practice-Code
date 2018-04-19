/*
* 
* Program name        : Print Array Of Integer & String
* Program Description : Let's say you have an integer array and a string array.
* 						You have to write a single method
* 						printarray that can print all the elements of both arrays.
* 						The method should be able to 
* 						accept both integer and string arrays.
*   Date : 19-April-2018
*/

import java.util.Scanner;

/*
 * @author ram dafale
 * Generic method printarray is printing integer 
 * array list and string array list together.
 */
public class printArrayOfIntString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Scanner read = new Scanner(System.in);
		int range1;
		int range2;
		int inti;
		int stringi;

		System.out.println("Enter number of elements for integer array\t");
		range1 = read.nextInt();
		Integer intArr[] = new Integer[range1];
		for (inti = 0; inti < range1; inti++) {
			intArr[inti] = read.nextInt();
		}
		System.out.println("Enter number of elements for string array\t");
		range2 = read.nextInt();
		String[] stringArray = new String[range2];
		for (stringi = 0; stringi < range2; stringi++) {
			stringArray[stringi] = read.nextLine();
		}
		System.out.println("-----------------------------------");
		printarray(intArr); // pass an Integer array
		printarray(stringArray); // pass a String array
		read.close();
	}

	public static <E> void printarray(E[] inputArray) {
		// display array elements
		for (E element : inputArray)
			System.out.printf("%s ", element);

		System.out.println();

	}
}
