package repository;

public interface CustomerDAO {

	String addCustomer(String customerId, String customerName,
			String customerAddress, String paymentMode, String retailerId);

	String removeCustomer(String customerId);

	String updateCustomer(String customerId);

}
