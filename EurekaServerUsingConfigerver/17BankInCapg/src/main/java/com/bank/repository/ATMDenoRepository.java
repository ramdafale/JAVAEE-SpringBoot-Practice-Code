package com.bank.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.model.ATM;
import com.bank.model.ATMDenomination;
import com.bank.model.Account;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface ATMDenoRepository extends JpaRepository<ATMDenomination, BigDecimal> {

	
	Optional<ATMDenomination> findBydenomination(BigDecimal myValue);
	
}
