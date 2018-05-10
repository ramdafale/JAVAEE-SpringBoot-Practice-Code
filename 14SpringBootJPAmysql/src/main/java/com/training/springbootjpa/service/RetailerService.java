package com.training.springbootjpa.service;

import java.util.List;

import com.training.springbootjpa.model.Retailer;

public interface RetailerService {

	public Retailer addRetailer(Retailer retailer);

	public List<Retailer> deleteRetailerById(long deleteById);

	public Retailer updateRetailerById(long updateById);
	
	
	
}
