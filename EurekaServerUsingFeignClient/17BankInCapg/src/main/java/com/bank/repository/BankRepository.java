package com.bank.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bank.model.ATM;
import com.bank.model.Bank;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

	
	Optional<Bank> findBybankId(Long  bankId);
}
