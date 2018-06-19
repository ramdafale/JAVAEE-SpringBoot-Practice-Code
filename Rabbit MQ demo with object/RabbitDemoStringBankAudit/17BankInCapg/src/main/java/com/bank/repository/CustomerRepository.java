package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.model.Customer;
/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findOneByCustomerName(String CustomerName);
}
