/**
 * 
 */
package repository;

import java.util.List;

import model.Goods;

/**
 * @author trainee
 *
 */
public interface RetailerDAO {

	
	
	
public  List<Goods> viewGoods();

public int  viewCustomer(int customerId);

public int  viewSupplier(int supplierId);
	
	
}
