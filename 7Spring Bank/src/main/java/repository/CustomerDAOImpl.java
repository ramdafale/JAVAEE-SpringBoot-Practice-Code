/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * @author trainee
 *
 */
public class CustomerDAOImpl implements ICustomerDao
{

	public List<Customer> addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		List<Customer> list = new ArrayList();
		list.add(customer);
		return list;

	}
}