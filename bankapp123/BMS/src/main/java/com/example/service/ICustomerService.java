package com.example.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.model.Bank;
import com.example.model.Customer;

public interface ICustomerService {

	Customer add(Customer customer);

	Object viewDetails(Customer customer);

	
 Optional<Bank> getBankDetailById(BigDecimal id) ;
}
