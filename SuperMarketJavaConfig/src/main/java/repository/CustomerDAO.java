package repository;

import model.Customer;

public interface CustomerDAO {

	
	public int addCustomer(Customer c);
	
	
	public int updateCustomer(int customerID, String name);
	
	
	public int removeCustomer(int id);
}
