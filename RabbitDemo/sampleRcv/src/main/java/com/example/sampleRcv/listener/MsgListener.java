package com.example.sampleRcv.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.commonDemo.TransactionNew;
import com.example.sampleRcv.dto.Transaction;
import com.example.sampleRcv.repository.DemoRepository;


@Component
public class MsgListener {

	@Autowired
	DemoRepository demoRepository;
	
	 public void receiveMessage(TransactionNew message) {
	       System.out.println("Received <" + message + ">");
	       Transaction transaction = new Transaction(message.getTransactionId(), message.getCustomerId(), message.getAccountId(), message.getAmount(), message.getTransactionType());	       
	       demoRepository.save(transaction);
	    }
}
