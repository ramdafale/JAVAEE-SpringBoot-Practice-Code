
import java.io.*;
 class Denomination
 {
 public static void main(String args[])throws IOException
 {
 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 int den[]={1000,500,100,50,20,10,5,2,1}; //storing all the denominations in an array
 System.out.print("Enter any Amount: "); //Entering an amount
 int amount=Integer.parseInt(br.readLine());
 
 int copy=amount; //Making a copy of the amount
 int totalNotes=0,count=0;
 
 System.out.println("\nDENOMINATIONS: \n");
 
 for(int i=0;i<9;i++) 
 {
 count=amount/den[i]; 
 if(count!=0) 
 {
 System.out.println(den[i]+"\tx\t"+count+"\t= "+den[i]*count);
 }
 totalNotes=totalNotes+count; //finding the total number of notes
 amount=amount%den[i]; //finding the remaining amount whose denomination is to be found
 }
 
 System.out.println("-");
 System.out.println("TOTAL= "+copy); //printing the total amount
 System.out.println("--------------------------------");
 System.out.println("Total Number of Notes\t= "+totalNotes); //printing the total number of notes
 }
 }