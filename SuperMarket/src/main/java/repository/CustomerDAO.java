package repository;

public interface CustomerDAO {

	int addCustomer(int customerId, String customerName,
			String customerAddress, String paymentMode);

	String removeCustomer(int customerId);

	String updateCustomer(int customerId);

}
