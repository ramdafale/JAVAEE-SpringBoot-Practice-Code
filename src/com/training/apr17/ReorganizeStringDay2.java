
/*******************************************************************************************************************
 * Program Name: 		 String Reorganizing
 * Program Description : Take a word and make sure that no two letter occuur adjacent to one another.
 * 
 *Programmer Name: 		Ram Dafale
 *Release Date:			17-April-2018
 ********************************************************************************************************************/


package com.cg;


/*
 * Class Name: ReorganizeString Program Description :
 * Description: It takes input from user and 
 * 
 */


import java.util.Scanner;


class myclass
{

	
public void MyMethod()
{
			System.out.println("Enter a String:");
			
			Scanner scan = new Scanner(System.in);
			int cnt = 0;
			String str = "";
			String string = scan.next();
			char arr[] = string.toCharArray();
			for (int input = 0; input < arr.length - 1; input++) {
				if (arr[input] != arr[input + 1]) {
					System.out.println(string);
				}
				break;
			}

			for (int input = 0; input < arr.length - 1; input++) {

				if (arr[input] == arr[input + 1]) {

					cnt++;
					continue;
				} else {

					char temp = arr[input];
					arr[input] = arr[input + 1];
					arr[input + 1] = temp;
					str = String.valueOf(arr);
				}
			}
			if (cnt != 3) {
				// System.out.println(str.length());
				for (int j = 0; j < str.length() - 1; j++) {

					if (str.charAt(j) == str.charAt(j + 1)) {
						System.out.println("null");
					} else {
						System.out.println("String is" + str);
						break;
					}

				}

			} else {
				System.out.println(str);
			}
			scan.close();
		}

}




public class CorrectionInReorganizeString {

	public static void main(String[] args) {
		
		myclass myob = new myclass();
		myob.MyMethod();
}
}
