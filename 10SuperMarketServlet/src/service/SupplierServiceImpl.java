package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.SupplierDAO;

/**
 * this class implementing methods of its parent interface
 */

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	/**
	 * @param supplierDAO
	 *            this will be used to access DAO methods
	 */
	@Autowired
	private SupplierDAO supplierDAO;

	/**
	 * @param supplierDAO
	 */

	@Override
	public int addSupplier(final int supplierId, final String supplierName, final String supplierAddress,
			final int quantityOrder, final int orderId, final double amount) {

		// SupplierDAO supplierDAO = null;
		final int addData = supplierDAO.addSupplier(supplierId, supplierName, supplierAddress, quantityOrder, orderId,
				amount);
		return addData;

	}

	@Override
	public int removeSupplier(final int supplierId) {
		// final SupplierDAO supplierDAO = null;
		final int removeData = supplierDAO.removeSupplier(supplierId);
		return removeData;
	}

	@Override
	public int updateSupplier(final int supplierId, final String supplierName) {
		final SupplierDAO supplierDAO = null;
		int removeData = supplierDAO.updateSupplier(supplierId, supplierName);
		return removeData;
	}

}
