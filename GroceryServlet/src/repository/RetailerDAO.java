package repository;

import java.util.List;
import model.Customer;
import model.Goods;
import model.Supplier;

public interface RetailerDAO {

	String addRetailerAndGetId(String retailerId, String retailerName, String retailerAddress);

	public List<Customer> viewCustomer(String retailerId);

	public List<Supplier> viewSupplier(String retailerId);

	public List<Goods> viewGoods(String retailerId);
}
