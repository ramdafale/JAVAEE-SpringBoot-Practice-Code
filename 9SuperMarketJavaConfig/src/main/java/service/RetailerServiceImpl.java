/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.Customer;
import model.Goods;
import model.Supplier;
import repository.CustomerDAO;
import repository.RetailerDAO;
import repository.RetailerDAOImpl;

/**
 * @author trainee
 *
 */
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private RetailerDAO retailerDao;

	@Override
	public int addRetailer(final Supplier s) {

		return retailerDao.addRetailer(s);

	}

	public List<Customer> viewCustomer(int customerId ) {
		List customerList = new ArrayList<Customer>();
		customerList = retailerDao.viewCustomer(customerId);
		return customerList;
	}

	@Override
	public List<Supplier> viewSupplier(final int supplierId) {
		// TODO Auto-generated method stub
		return retailerDao.viewSupplier(supplierId);
	}

	@Override
	public List<Goods> viewGoods() {
		// TODO Auto-generated method stub
		return retailerDao.viewGoods();
	}

}
