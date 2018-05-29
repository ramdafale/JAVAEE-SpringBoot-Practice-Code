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
@FeignClient("bank-zuul-service")
public interface IFeignClient {

	@PostMapping(path = "/auditLog")
	AuditLog create(final AuditLog audit);

}
