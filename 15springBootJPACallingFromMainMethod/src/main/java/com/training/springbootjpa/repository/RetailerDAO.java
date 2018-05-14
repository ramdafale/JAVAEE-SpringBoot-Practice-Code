package com.training.springbootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.springbootjpa.model.Retailer;

public interface RetailerDAO extends JpaRepository<Retailer, Long> {
}
