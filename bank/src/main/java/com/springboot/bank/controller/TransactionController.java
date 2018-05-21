/**
 * 
 */
package com.springboot.bank.controller;

import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Transaction;
import com.springboot.bank.service.TransactionService;

/**
 * @author Sumit
 *
 */

@RestController
public class TransactionController {

	final Logger LOGGER = Logger.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/createTransaction")
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) throws BankException {
		String transactionData = null;
		try {
			transactionData = transactionService.createTransaction(transaction);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (transactionData == null)
			throw new BankException("Transaction details not added");
		else
			return new ResponseEntity<String>(transactionData, HttpStatus.OK);
	}

	@PostMapping(value = "/viewTransaction/{customerId}")
	public ResponseEntity<Optional<Transaction>> getTransactionDetails(@PathVariable Long customerId)
			throws BankException {
		Optional<Transaction> transactionData = null;
		try {
			transactionData = transactionService.getTransactionDetails(customerId);
		} catch (BankException e) {
			LOGGER.error(e.getMessage());
		}
		if (transactionData == null)
			throw new BankException("Transaction details not found");
		else
			return new ResponseEntity<Optional<Transaction>>(transactionData, HttpStatus.OK);
	}

}
