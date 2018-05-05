/**
 * 
 */
package service;

import java.util.List;

import model.Customer;
import model.Goods;
import model.Supplier;

/**
 * @author trainee
 *
 */
public interface RetailerService {

	//int addSupplier(String supplierName, String supplierAddress);

	int addRetailer(Supplier s);
	
	
public  List<Goods> viewGoods();

public List<Customer>  viewCustomer(int customerId );

public  List<Supplier>  viewSupplier(int supplierId);
	

}
