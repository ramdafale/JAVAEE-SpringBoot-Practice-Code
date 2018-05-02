package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Customer;
import repository.CustomerDAO;

/**
 * @author Ram
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	/**
	 * @param customerDAO
	 */
	public CustomerServiceImpl(final CustomerDAO customerDAO) {
		super();
		this.customerDAO = customerDAO;
	}

	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	public int addCustomer(final Customer c) {
		final int addData = customerDAO.addCustomer(c);
		return addData;
	}

	public int updateCustomer(int customerId, String customerName) {
		int updateData = customerDAO.updateCustomer(customerId, customerName);
		return updateData;
	}

	@Override
	public int removeCusotmer(int customerId) {
		int removeData = customerDAO.removeCustomer(customerId);
		return removeData;
	}

}
