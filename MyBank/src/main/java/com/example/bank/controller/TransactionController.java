package com.example.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.TransactionOperation;
import com.example.bank.service.TransactionServiceImpl;


/**
 * @author ram
 *
 */

@RestController
public class TransactionController {

	@Autowired
	private TransactionServiceImpl transactionService;

	@PostMapping(value = "/createTransaction")
	public ResponseEntity<String> createTransaction(@RequestBody TransactionOperation transaction) throws ManagedException {
		String transactionData = null;
		try {
			transactionData = transactionService.createTransaction(transaction);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (transactionData == null)
			throw new ManagedException("Transaction details not added");
		else
			return new ResponseEntity<String>(transactionData, HttpStatus.OK);
	}

	@PostMapping(value = "/getTransactionDetails/{customerId}")
	public ResponseEntity<Optional<TransactionOperation>> getTransactionDetails(@PathVariable Long customerId)
			throws ManagedException {
		Optional<TransactionOperation> transactionData = null;
		try {
			transactionData = transactionService.getTransactionDetails(customerId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (transactionData == null)
			throw new ManagedException("Transaction details not found");
		else
			return new ResponseEntity<Optional<TransactionOperation>>(transactionData, HttpStatus.OK);
	}

}
