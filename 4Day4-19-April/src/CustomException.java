import java.util.Scanner;

/**
 * 
 */




/**
 * @author trainee
 *
 */


class InputFormatException extends Exception
{
	public InputFormatException(String Message)
	{
		super(Message);
		
	}
}

public class CustomException {

/**
* @param args
* @return 
 * @throws InputFormatException 
*/
public static int power(int numrs,int pows) throws InputFormatException  //throws InputFormatException
{
	
	if (numrs < 0)
	{
		throw new InputFormatException("Negative value not allowed");
		
	}
	
if (pows > 0 && numrs > 0  )
{
        return numrs * power(numrs, pows - 1);
}
  else 
	 { 
	 return 1;
	 }
 
   // else {
   //     	throw new InputFormatException("Negative value not allowed");
   // }
}
public static void main(String[] args) throws InputFormatException {
Scanner read=new Scanner(System.in);
int numb1;
int numb2;
System.out.println("Enter two numbers\t");
numb1=read.nextInt();
numb2=read.nextInt();
//long  rest=;
System.out.println(power(numb1,numb2));
 
}

}