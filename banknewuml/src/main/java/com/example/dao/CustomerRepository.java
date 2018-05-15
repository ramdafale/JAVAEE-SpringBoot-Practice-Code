/**
 * 
 */
package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Customer;

/**
 * @author trainee
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
