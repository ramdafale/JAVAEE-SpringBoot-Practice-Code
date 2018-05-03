package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SupplierDAO;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDAO supplierDAO;

	/**
	 * @param supplierDAO
	 */
	public SupplierServiceImpl(SupplierDAO supplierDAO) {
		super();
		this.supplierDAO = supplierDAO;
	}

	@Override
	public String addSupplier(String supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount,  String retailerId) {
		String addData = supplierDAO.addSupplier(supplierId, supplierName,
				supplierAddress, quantityOrder, orderId, amount, retailerId);
		return addData;

	}

	@Override
	public String removeSupplier(String supplierId) {
		String removeData = supplierDAO.removeSupplier(supplierId);
		return removeData;
	}

	@Override

	public String updateSupplier(String supplierId) {
		String removeData = supplierDAO.updateSupplier(supplierId);
		return removeData;
	}

}
