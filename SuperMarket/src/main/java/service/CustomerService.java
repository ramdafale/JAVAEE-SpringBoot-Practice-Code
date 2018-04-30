package service;

public interface CustomerService {

	public int addCustomer(int customerId, String customerName,String customerAddress ,String paymentMode);

	public String removeCustomer(int goodsId);

	public String updateCustomer(int goodsId);
	
}
