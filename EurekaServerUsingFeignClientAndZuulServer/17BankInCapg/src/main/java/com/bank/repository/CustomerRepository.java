package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Customer;
/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findOneBycustomerName(String CustomerName);
	
	Optional<Customer> findBycustomerId(Long customerId);
}
