/**
 * 
 */
package com.example.bank.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.TransactionOperation;

/**
 * @author trainee
 *
 */
public interface ITransactionService     {

	
	
	
	public TransactionOperation createTransaction(TransactionOperation trans) throws ManagedException;

	public List<TransactionOperation> generateTransactionReport() throws ManagedException;
	
}
