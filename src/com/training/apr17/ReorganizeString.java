package com.tarining.apr17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ReorganizeString {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str, output;
		int d, p, q;
		List<Character> characters = new ArrayList<>();
		System.out.print("Please enter the string: ");
		str = input.nextLine();
		for (int i = 0; i < str.length(); i++) {
			characters.add(str.charAt(i));
		}
		Iterator itr = characters.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next());
		}
		System.out.println();
		d = str.length() - 2;
		Collections.rotate(characters, -d);
		
		System.out.print(characters);
		
	}

}
