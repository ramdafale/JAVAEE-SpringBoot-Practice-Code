package com.training.springbootjpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;
import com.training.springbootjpa.repository.CustomerDAO;

/**
 * @author RamDAfale
 *
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Customer addCustomer(Customer customer) throws ManagedException {
		Customer customerData = customerDAO.save(customer);
		if ( customerData.getCustomerName()==null || customerData.getPaymentMode() == null )
		{
			throw new ManagedException("provide correct details");
		}
		else
			
			return customerData;
	}

	
	
	
	
	
	
	@Override
	public Customer deleteCustomerById(long deleteById) throws ManagedException {
		
		Optional<Customer> customer=customerDAO.findById(deleteById);
		if(customer.isPresent())
		{
		 customerDAO.deleteById(deleteById);		
		}
		else
		{
			throw new ManagedException("Id not Found");
			
		}
		return null;
			}
		
	

	@Override
	public Customer updateCustomerById(long updateById) {
		Optional<Customer> customer = customerDAO.findById(updateById);
		Customer customerDummy = customer.get();
		customerDummy.setCustomerAddress("Newyork");
		return customerDummy;
	}

	@Override
	public List<Customer> getCustomer() {

		return (List<Customer>) customerDAO.findAll();
	}

	@Override
	public String getCustomerDetail(Long id) throws ManagedException {

		Optional<Customer> customerData = customerDAO.findById(id);
		if (customerData.isPresent()) {
			return "Data Found";
		} else
			return "Not Found";
	}

}
