package service;

/**
 * this interface contains various operations.
 */
public interface SupplierService {

	/**
	 * this method is used to invoke the Repository method addSupplier
	 * which will hit the databases.
	 * 
	 */
	 int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);
	 /**
		 * this method is used to invoke the Repository method removeSupplier
		 * which will hit the databases.
		 * 
		 */
	 int removeSupplier(int supplierId);

	 /**
		 * this method is used to invoke the Repository method addSupplier
		 * which will hit the databases.
		 * 
		 */
	 int updateSupplier(int supplierId,String supplierName);

}
