package com.training.java;

/************************************************************************************
 * Program Name: Reverse String
 * Description : Check if more than  
 * 				 must be true or else false.
 * Programmer Name: Ram Dafale
 * DateOfRelease: 16-April-2018
 ***********************************************************************************/




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) throws IOException {
	
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        String input = br.readLine();
		        String[] words = input.split(" "); // logic uses space as a delimter to separate words.
		        String reverse = ""; 
		        for (int i = 0; i < words.length; i++)
		        {
		            for (int j = words[i].length() - 1; j >= 0; j--) 
		            {
		                reverse += words[i].charAt(j);
		            }
		            System.out.print(reverse + " ");
		            reverse = "";
		        }
		    }
		}
	


	




















