package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.model.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank,Long> {

}
