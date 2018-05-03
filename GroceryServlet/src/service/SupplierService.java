package service;

public interface SupplierService {

	public String addSupplier(String supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount, String retailerId);

	public String removeSupplier(String supplierId);

	public String updateSupplier(String supplierId);
}
