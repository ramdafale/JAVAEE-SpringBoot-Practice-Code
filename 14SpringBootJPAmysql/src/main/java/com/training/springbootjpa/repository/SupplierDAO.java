package com.training.springbootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.springbootjpa.model.Supplier;

public interface SupplierDAO extends JpaRepository<Supplier, Long> {
}
