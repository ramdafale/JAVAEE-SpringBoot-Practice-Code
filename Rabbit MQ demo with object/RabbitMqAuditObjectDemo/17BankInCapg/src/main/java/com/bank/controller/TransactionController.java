package com.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.dto.TransactionReq;
import com.bank.model.Transaction;
import com.bank.service.ITransactionService;

@RestController
public class TransactionController {
	@Autowired
	private ITransactionService transactionService;

	
	
	// this will return all the statements of account id
  @GetMapping("/transactionDetails/{accountId}")
	public ResponseEntity<?> retriveTrans(@PathVariable final Long accountId) {
	  
		List<Transaction> list;
		try {
			list = transactionService.generateTransactionReport( accountId);
			return new ResponseEntity<List<Transaction>>(list, HttpStatus.OK);
		} catch (ManagedException e) {
			String message = e.getMessage();
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
	}
	
  //this will create statements instance which will be used to tract transactions made by user
  
	@PostMapping("/transactionCreate")
	public ResponseEntity<?> createTransaction(@RequestBody final TransactionReq trans) {

		final Transaction transact =trans.getTransaction();
         final  Transaction newtrans;
		try {
			newtrans = transactionService.createTransaction(transact);
			  return new ResponseEntity<Transaction>(newtrans, HttpStatus.CREATED);
		} catch (ManagedException e) {
			// TODO Auto-generated catch block
			String msg=e.getMessage();
			  return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
        
			
	}
}
