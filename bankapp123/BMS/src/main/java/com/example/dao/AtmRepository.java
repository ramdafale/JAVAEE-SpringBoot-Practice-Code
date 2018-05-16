package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Bank;

public interface AtmRepository   extends JpaRepository<Bank, Long> {

}
