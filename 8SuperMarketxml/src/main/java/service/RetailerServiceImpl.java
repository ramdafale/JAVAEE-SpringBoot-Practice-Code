/**
 * 
 */
package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.Customer;
import model.Goods;
import model.Supplier;
import repository.CustomerDAO;
import repository.RetailerDAO;

/**
 * @author trainee
 *   
 */
public class RetailerServiceImpl implements RetailerService {

	
	private RetailerDAO retailerDao;

	@Override
	public int addRetailer(final Supplier s) {

		return retailerDao.addRetailer(s);

	}

	@Override
	public List<Customer> viewCustomer(final int customerId) {
		
		return retailerDao.viewCustomer(customerId);
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
