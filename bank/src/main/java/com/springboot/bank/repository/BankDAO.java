/**
 * 
 */
package com.springboot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.bank.model.Bank;

/**
 * @author Sumit
 *
 */
public interface BankDAO extends JpaRepository<Bank, Long> {

}
