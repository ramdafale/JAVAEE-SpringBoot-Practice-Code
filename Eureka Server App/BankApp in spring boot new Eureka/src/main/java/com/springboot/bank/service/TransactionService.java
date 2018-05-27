/**
 * 
 */
package com.springboot.bank.service;

import java.util.Optional;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Transaction;

/**
 * @author Ram
 *this interface has 2 methods which create a statements for an Customer
 *and generate a report for statements
 */
public interface TransactionService {

	String createTransaction(Transaction transaction) throws BankException;

	Optional<Transaction> getTransactionDetails( Long customerId) throws BankException;
}
