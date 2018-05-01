package service;

public interface SupplierService {

	public int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);

	public String removeSupplier(int supplierId);

	public String updateSupplier(int supplierId);

}
