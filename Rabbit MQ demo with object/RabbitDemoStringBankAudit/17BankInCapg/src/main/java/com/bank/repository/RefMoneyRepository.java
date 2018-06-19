package com.bank.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.RefMoney;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
public interface RefMoneyRepository extends JpaRepository<RefMoney,BigDecimal> {

}
