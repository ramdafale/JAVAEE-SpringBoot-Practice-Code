package com.training.springbootjpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.springbootjpa.model.Supplier;
import com.training.springbootjpa.repository.SupplierDAO;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDAO supplierDAO;

	@Override
	public Supplier addSupplier(Supplier supplier) {
		Supplier supplierData = supplierDAO.save(supplier);
		return supplierData;
	}

	@Override
	public List<Supplier> deleteSupplierById(long deleteById) {
		List<Supplier> supplierList = supplierDAO.findAll();
		Iterator iterator = supplierList.iterator();
		while (iterator.hasNext()) {
			Supplier supplier = (Supplier) iterator.next();
			if (supplier.getSupplierId() == deleteById) {
				supplierDAO.deleteById(deleteById);
			}
		}
		return supplierList;
	}

	@Override
	public Supplier updateSupplierById(long updateById) {
		Optional<Supplier> supplier = supplierDAO.findById(updateById);
		Supplier supplierDummy = supplier.get();
		supplierDummy.setQuantityOrder(123);
		return supplierDummy;
	}

}
