package com.bank.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.ATMDenomination;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface ATMDenoRepository extends JpaRepository<ATMDenomination, BigDecimal> {

}
