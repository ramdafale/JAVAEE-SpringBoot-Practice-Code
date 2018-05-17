package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	

}
