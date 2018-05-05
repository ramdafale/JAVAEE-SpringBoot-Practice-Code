package repository;

import model.Customer;

public interface CustomerDAO {

	
	public int addCustomer(Customer e);
	public int updateCustomer(int customerID, String name);
	public int removeCustomer(int id);
}
