
package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Customer;
import model.Goods;
import model.Supplier;
import repository.RetailerDAO;

/**
 * @author Sumit
 *
 */

@Service("retailerService")
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private RetailerDAO retailerDAO;

	/**
	 * @param retailerDAO
	 */
	public RetailerServiceImpl(RetailerDAO retailerDAO) {
		super();
		this.retailerDAO = retailerDAO;
	}

	public String addRetailerAndGetId(String retailerId, String retailerName, String retailerAddress) {
		String idOfRetailer = retailerDAO.addRetailerAndGetId(retailerId, retailerName, retailerAddress);
		return idOfRetailer;
	}

	public List<Customer> viewCustomer(String retailerId) {
		List customerList = new ArrayList<Customer>();
		customerList = retailerDAO.viewCustomer(retailerId);
		return customerList;
	}

	public List<Supplier> viewSupplier(String retailerId) {
		List supplierList = new ArrayList<Supplier>();
		supplierList = retailerDAO.viewSupplier(retailerId);
		return supplierList;
	}

	public List<Goods> viewGoods(String retailerId) {
		List goodsList = new ArrayList<Goods>();
		goodsList = retailerDAO.viewGoods(retailerId);
		return goodsList;
	}
}
