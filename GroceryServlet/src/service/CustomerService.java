package service;

public interface CustomerService {

	String addCustomer(String customerId, String customerName,
			String customerAddress, String paymentMode, String retailerId);

	String removeCusotmer(String customerId);

	String updateCustomer(String customerId);
}
