package repository;

public interface SupplierDAO {

	int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);

	String removeSupplier(int supplierId);

	String updateSupplier(int supplierId);

}
