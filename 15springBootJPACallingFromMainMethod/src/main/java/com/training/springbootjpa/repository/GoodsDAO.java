package com.training.springbootjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.springbootjpa.model.Goods;

public interface GoodsDAO extends JpaRepository<Goods, Long>{

}
