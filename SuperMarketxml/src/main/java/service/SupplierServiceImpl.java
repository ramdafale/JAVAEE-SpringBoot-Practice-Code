package service;

import repository.SupplierDAO;

public class SupplierServiceImpl implements SupplierService {

	@Override
	public int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount) {

		SupplierDAO supplierDAO = null;
		int addData = supplierDAO.addSupplier(supplierId, supplierName,
				supplierAddress, quantityOrder, orderId, amount);
		return addData;

	}

	@Override
	public int removeSupplier(int supplierId) {
		SupplierDAO supplierDAO = null;
		int removeData = supplierDAO.removeSupplier(supplierId);
		return removeData;
	}

	@Override
	public int updateSupplier(int supplierId) {
		SupplierDAO supplierDAO = null;
		int removeData = supplierDAO.updateSupplier(supplierId);
		return removeData;
	}

}
