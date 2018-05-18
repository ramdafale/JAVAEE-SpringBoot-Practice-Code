/**
 * 
 */
package com.example.bank.service;

import java.util.Optional;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.TransactionOperation;

/**
 * @author trainee
 *
 */
public interface ITransactionService     {

	
	
	
	public String createTransaction(TransactionOperation trans) throws ManagedException;

	public Optional<TransactionOperation> getTransactionDetails(Long customerId) throws ManagedException;
	
}
