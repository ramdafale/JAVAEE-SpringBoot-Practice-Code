package com.example.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.TransactionOperation;

public interface TransactionRepository  extends JpaRepository<TransactionOperation, Long> {
	

}