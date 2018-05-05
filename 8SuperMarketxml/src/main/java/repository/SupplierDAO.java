package repository;

public interface SupplierDAO {

	public int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);

	public int removeSupplier(int supplierId);
/*
	public int updateSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount);
*/	
	public int updateSupplier(int supplierId, String supplierName);

}
