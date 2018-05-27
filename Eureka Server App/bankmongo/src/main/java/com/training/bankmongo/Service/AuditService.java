/**
 * 
 */
package com.training.bankmongo.Service;

import com.training.bankmongo.Model.Audit;

/**
 * @author ram
 *
 */

public interface AuditService {

	Audit createAudit(Audit audit);

	Audit getAudit(String uuid);

	Audit deleteAudit(String uuid);

	Audit updateAudit(String uuid);

	
}
