package com.springboot.bank.service;

import com.springboot.bank.model.Audit;
import com.springboot.bank.model.Customer;

public interface IAuditService {

	void auditLogService(Audit audit );
}
