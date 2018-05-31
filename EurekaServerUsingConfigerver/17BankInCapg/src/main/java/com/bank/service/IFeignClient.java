/**
 * 
 */
package com.bank.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import com.bank.model.AuditLog;

/**
 * @author trainee
 *
 */
@FeignClient("mongo-client")
public interface IFeignClient {

	@PostMapping(path = "/auditLog")
	AuditLog create(final AuditLog audit);

}
