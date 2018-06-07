package com.example.sampleSend.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commonDemo.TransactionNew;
import com.example.sampleSend.SampleSendApplication;

@RestController
public class AppController {

	 private RabbitTemplate rabbitTemplate;

	/**
	 * @param rabbitTemplate
	 */
	public AppController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	 
	@RequestMapping("/")
	public String sendMsg(@RequestBody TransactionNew transaction) {
		rabbitTemplate.convertAndSend(SampleSendApplication.MESSAGE_QUEUE, transaction);
		return "send";
	}
}
