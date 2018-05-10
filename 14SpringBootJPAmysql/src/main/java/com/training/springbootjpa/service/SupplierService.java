package com.training.springbootjpa.service;

import java.util.List;
import com.training.springbootjpa.model.Supplier;

public interface SupplierService {

	public Supplier addSupplier(Supplier supplier);

	public List<Supplier> deleteSupplierById(long deleteById);

	public Supplier updateSupplierById(long updateById);
}
