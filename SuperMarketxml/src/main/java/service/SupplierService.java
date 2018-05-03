package service;

public interface SupplierService {

	public int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);

	public int removeSupplier(int supplierId);

	public int updateSupplier(int supplierId);

}
