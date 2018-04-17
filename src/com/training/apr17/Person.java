package com.tarining.apr17;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Details {

	private String fname, lname;
	private int number;
	private static int id = 0;

	public Details(String fname, String lname, int number) {
		this.fname = fname;
		this.lname = lname;
		int cnt = 0;
		id++;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public int getNumber() {
		return number;
	}

	public static int getId() {
		return id;
	}

}

public class Person extends Details {
	public Person(String fname, String lname, int number) {
		super(fname, lname, number);

	}

	public static void main(String args[]) {
		int choice;
		List<Details> list = new ArrayList<>();
		while (true) {
			System.out.println("Enter the choice to do");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			switch (choice) {

			case 1: {
				System.out.println("ENter the details");

				Scanner sc1 = new Scanner(System.in);
				String first = sc.next();
				String last = sc.next();
				int num = sc.nextInt();

				// break;

				Details d1 = new Details(first, last, num);

				list.add(d1);
			}
			case 2: {
				System.out.println("Remove the details of a person");
				Scanner sc1 = new Scanner(System.in);
				int RemoveById = sc1.nextInt();
				list.remove(RemoveById);
			}
				case3: {
					System.out.println("Search the details of a person by name");
					Scanner sc1 = new Scanner(System.in);
					String SearchByName = sc1.next();

					boolean isFound = list.contains(SearchByName);
					if (isFound == true)
						System.out.println("Record found");
					else
						System.out.println("Record not found");
				}

			}
		}

	}
}
