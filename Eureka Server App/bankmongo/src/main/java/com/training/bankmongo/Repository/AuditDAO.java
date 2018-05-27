/**
 * 
 */
package com.training.bankmongo.Repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.training.bankmongo.Model.Audit;

/**
 * @author ram
 *
 */
public interface AuditDAO extends MongoRepository<Audit, String> {

	
	
	Audit findByeventName(String name);
	Long deleteByeventName(String name);
	Audit findByeventType(String name2);
	
}
