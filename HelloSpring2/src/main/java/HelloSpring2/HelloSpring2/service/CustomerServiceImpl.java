/**
 * 
 */
package HelloSpring2.HelloSpring2.service;

import java.util.List;

import HelloSpring2.HelloSpring2.Customer;
import HelloSpring2.HelloSpring2.repository.CustomerRepository;
import HelloSpring2.HelloSpring2.repository.CustomerRepositoryImpl;

/**
 * @author trainee
 *
 */
public class CustomerServiceImpl implements CustomerService {

	
	
	// hardcoded reference
	private CustomerRepository customerRepository = new CustomerRepositoryImpl();
	
	/* (non-Javadoc)
	 * @see HelloSpring2.HelloSpring2.service.CustomerService#findAll()
	 */
	
	
	public List<Customer> findAll()
	{
		return customerRepository.AddAllCustomer();
	}
	
	
}
