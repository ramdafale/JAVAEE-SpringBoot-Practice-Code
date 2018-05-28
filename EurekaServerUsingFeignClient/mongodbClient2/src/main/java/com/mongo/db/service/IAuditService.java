package com.mongo.db.service;

import java.util.List;
import java.util.UUID;

import com.mongo.db.document.AuditLog;
import com.mongo.db.exception.AuditException;

public interface IAuditService {
	
	/**
	 * @method createAudit
	 * @param audit
	 * @return AuditLog object 
	 * @throws AuditException
	 */
	AuditLog createAudit(AuditLog audit) throws AuditException;
	/**
	 * @method getDetails
	 * @param eventName
	 * @return List<AuditLog>
	 * @throws AuditException
	 */
	List<AuditLog> getDetails(String eventName) throws AuditException;
	/**
	 * @method  deleteaudit
	 * @param id
	 * @return String
	 * @throws AuditException
	 */
	String deleteAudit(UUID id) throws AuditException;
	/**
	 * @method update
	 * @param id
	 * @param eventType
	 * @return AuditLog
	 * @throws AuditException
	 */
	AuditLog update(UUID id,String eventType) throws AuditException;
	/**
	 * @method findall
	 * @return List<AuditLog>
	 * @throws AuditException
	 */
	List<AuditLog> findall() throws AuditException;

}
