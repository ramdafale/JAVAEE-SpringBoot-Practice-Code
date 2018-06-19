package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bank.model.Bank;


/**
 * @author ram
 * this interface provide various methods of JPAREPOSITORY
 * to deal with operations
 *
 */
@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

}
