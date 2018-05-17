/**
 * 
 */
package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.TransactionOperation;
import com.example.bank.repository.TransactionRepository;

/**
 * @author ram
 *
 */
@Service("transactionService")
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private TransactionRepository transact;
	@Override
	public TransactionOperation createTransaction(final TransactionOperation trans) throws ManagedException {
		final TransactionOperation transaction =transact.save(trans);
		if(transaction!=null)
		{
		return transaction;
		}
		else
		{
			throw new ManagedException("no transaction has been created");
		}
	}

	@Override
	public List<TransactionOperation> generateTransactionReport() throws ManagedException {
		// TODO Auto-generated method stub
		List<TransactionOperation> tran=transact.findAll();
		if(tran.isEmpty())
		{
			throw new ManagedException("no list generated");
		}
		else
		{
		return tran;
		}
	}
	
	
	
	
}
