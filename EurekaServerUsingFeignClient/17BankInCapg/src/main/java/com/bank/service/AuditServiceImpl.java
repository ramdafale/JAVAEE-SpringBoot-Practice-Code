/*package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.model.AuditLog;

@Service
public class AuditServiceImpl {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// generate audit log
	public AuditLog generateAudit(final AuditLog audit) {

		final String uri = "http://mongo-client/createAudit";
		System.out.println("audit obnject " + audit);
		final AuditLog audii = restTemplate.postForObject(uri, audit, AuditLog.class);
		return audii;

	}

	
	 * @Autowired private DiscoveryClient discoveryClient;
	 * 
	 * 
	 * public AuditLog generateAudit(AuditLog auditData) { List<ServiceInstance>
	 * instance=discoveryClient.getInstances("MONGO-CLIENT"); ServiceInstance
	 * serviceInstance=instance.get(0); RestTemplate restTemplate = new
	 * RestTemplate();
	 * 
	 * String url =serviceInstance.getUri().toString(); url=url+"/create"; AuditLog
	 * audit = auditData; System.out.println(audit);; AuditLog resultOfOperation =
	 * restTemplate.postForObject(url, audit, AuditLog.class); return
	 * resultOfOperation; }
	 

}
*/