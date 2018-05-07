package service;

import model.Customer;

public interface CustomerService {


	//public String addCustomer(Customer e);
//	public String updateCustomer(int customerID, String name);
	//public int removeCustomer(int id);
	int addCustomer(Customer c);
	int updateCustomer(int customerId, String customerName);
	int removeCusotmer(int customerId);
}
