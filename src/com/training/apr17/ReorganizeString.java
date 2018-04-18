
/*******************************************************************************************************************
 * Program Name: 		 String Reorganizing
 * Program Description : Take a word and make sure that no two letter occuur adjacent to one another.
 * 
 *Programmer Name: 		Ram Dafale
 *Release Date:			17-April-2018
 ********************************************************************************************************************/

package com.tarining.apr17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*******************************************************************************************************************
 * Class Name: ReorganizeString Program Description :
 * Description: It takes input from user and 
 * 
 ********************************************************************************************************************/

public class ReorganizeString {

	public static void main(String[] args) {

		// Taking input form user and storing in to the variable

		System.out.print("Please enter the string: ");

		Scanner sc = new Scanner(System.in);

		String input = sc.nextLine();
		sc.close();
		int d;
		List<Character> charactersToPrint = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			charactersToPrint.add(input.charAt(i));
		}

		Iterator<Character> itr = charactersToPrint.iterator();

		System.out.println("  \n Number To be Altered ");

		while (itr.hasNext()) {
			System.out.print(itr.next());
		}

		// Logic to shuffle letters 
		int rotateMe = input.length() - 2;
		Collections.rotate(charactersToPrint, -rotateMe);

		System.out.print("Im running successfully");
		System.out.print(charactersToPrint);

	}

	/*
	 * Output:
	 * 
	 * 
	 * Please enter the string: aab aab [a, b, a]
	 * 
	 */

}
