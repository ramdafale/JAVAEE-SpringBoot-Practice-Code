/**
 * 
 */
package repository;

import java.util.List;

import model.Customer;
import model.Goods;
import model.Supplier;

/**
 * @author trainee
 *
 */
public interface RetailerDAO {

	
	int addRetailer(Supplier s);
	
public  List<Goods> viewGoods();

public List<Customer>  viewCustomer(int customerId );

public  List<Supplier>  viewSupplier(int supplierId);
	
	
}
