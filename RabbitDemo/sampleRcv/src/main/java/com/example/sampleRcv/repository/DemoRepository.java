/**
 * 
 */
package com.example.sampleRcv.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.sampleRcv.dto.Transaction;

@Repository
public interface DemoRepository extends MongoRepository<Transaction, Integer>{

}
