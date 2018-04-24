/**
 * 
 */
package HelloSpring2.HelloSpring2.repository;

import java.util.ArrayList;
import java.util.List;

import HelloSpring2.HelloSpring2.Customer;

/**
 * @author trainee
 *
 */
public class CustomerRepositoryImpl implements CustomerRepository {

	
	
	public List<Customer> AddAllCustomer() {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer("ram","dafale","Wardha",545458451,"ramdafale@gmail.com");
	
		customers.add(customer);
		return customers;
	}
}
