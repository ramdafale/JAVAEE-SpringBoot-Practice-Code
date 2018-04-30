package service;

import repository.CustomerDAO;

/**
 * @author Ram
 *
 */
public class CustomerServiceImpl implements CustomerService {

	@Override
	public int addCustomer(int customerId, String customerName,
			String customerAddress, String paymentMode) {

		CustomerDAO customerDAO = null;
		int addData = customerDAO.addCustomer(customerId, customerName,
				customerAddress, paymentMode);
		return addData;
	}

	@Override
	public String removeCusotmer(int customerId) {

		CustomerDAO customerDAO = null;
		String removeData = customerDAO.removeCustomer(customerId);
		return removeData;
	}

	@Override
	public String updateCustomer(int customerId) {

		CustomerDAO customerDAO = null;
		String updateData = customerDAO.updateCustomer(customerId);
		return updateData;
	}

}
