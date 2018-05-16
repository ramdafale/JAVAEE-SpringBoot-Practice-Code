/**
 * 
 */
package com.example.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Bank;

/**
 * @author trainee
 *
 */
public interface AccountRepository  extends JpaRepository<Bank, BigDecimal>  {

}
