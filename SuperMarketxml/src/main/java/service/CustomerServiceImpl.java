package service;



/**
 * @author Ram
 *
 */
public class CustomerServiceImpl implements CustomerService {

	@Override
	public int addCustomer(int customerId, String customerName,
			String customerAddress, String paymentMode) {

	//	CustomerDAO customerDAO = null;
		int addData = addCustomer(customerId, customerName,
				customerAddress, paymentMode);
		return addData;
	}
	

	@Override
	public String removeCustomer(int customerId) {

		//CustomerDAO customerDAO = null;
		String removeData = removeCustomer(customerId);
		return removeData;
	}

	@Override
	public String updateCustomer(int customerId) {

		//CustomerDAO customerDAO = null;
		String updateData = updateCustomer(customerId);
		return updateData;
	}

}
