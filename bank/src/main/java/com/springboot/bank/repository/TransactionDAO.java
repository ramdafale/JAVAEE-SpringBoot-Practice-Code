/**
 * 
 */
package com.springboot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.bank.model.Transaction;

/**
 * @author Sumit
 *
 */
public interface TransactionDAO extends JpaRepository<Transaction, Long> {

}
