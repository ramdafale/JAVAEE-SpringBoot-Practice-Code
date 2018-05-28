package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.model.Account;
import com.bank.model.Transaction;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	
	/*
	 * this query will find trasanction where account id is associated
	 */
	@Query(value="select * from transaction_details where account_account_id=?1",nativeQuery=true)
	List<Transaction> findAllByAccountId(Long accountId);

}
