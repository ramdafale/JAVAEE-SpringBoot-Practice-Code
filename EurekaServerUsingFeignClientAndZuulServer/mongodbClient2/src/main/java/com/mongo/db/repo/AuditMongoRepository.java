package com.mongo.db.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.db.document.AuditLog;

/**
 * @author trainee
 *
 */
public interface AuditMongoRepository extends MongoRepository<AuditLog,UUID>{
 
	/**
	 * @param eventName
	 * @return
	 */
	List<AuditLog> findByEventName(String eventName);
	/**
	 * @param eventType
	 * @return
	 */
	AuditLog findByEventType(String eventType);
	
	
	Optional<AuditLog> findByeventID(UUID eventID);
}
