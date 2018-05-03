package repository;

public interface SupplierDAO {

	String addSupplier(String supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount, String retailerId);

	String removeSupplier(String supplierId);

	String updateSupplier(String supplierId);
}
