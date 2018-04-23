/**
 * 
 */
package HelloSpring.HelloSpring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author trainee
 *
 */
public class Apps {

	public static void main(String args[])
	{
	
	  ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
      
      Customer customer1 = ctx.getBean(Customer.class);
      System.out.println("ram");
      
      System.out.println(customer1);
      
      
      
      
      
      
     
  //    Operations operations = ctx.getBean(Operations.class);
  //    operations.helloWorld();
  }
	

}