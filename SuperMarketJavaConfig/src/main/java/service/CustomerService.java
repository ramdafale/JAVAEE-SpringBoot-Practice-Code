package service;

import model.Customer;

public interface CustomerService {


	public int addCustomer(Customer e);
	public int updateCustomer(int customerID, String name);
	public int removeCustomer(int id);
}
