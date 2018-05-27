package com.springboot.bank.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.bank.model.Audit;
import com.springboot.bank.model.Customer;




@Service
public class AuditServiceImpl implements IAuditService {
	RestTemplate restTemplate = new RestTemplate();

	final Logger LOGGER = Logger.getLogger(AuditServiceImpl.class.getName());

	@Override
	public void auditLogService(Audit audit) {
		

		final String uri = "http://localhost:8281/Audit/create";

		restTemplate.postForObject(uri, audit, Audit.class);

	}

}
