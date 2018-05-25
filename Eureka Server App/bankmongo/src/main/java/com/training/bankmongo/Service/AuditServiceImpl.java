/**
 * 
 */
package com.training.bankmongo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.bankmongo.Model.Audit;
import com.training.bankmongo.Repository.AuditDAO;

/**
 * @author ram
 *
 */
@Service("auditService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	AuditDAO auditDAO;

	/*
	 * @see com.training.bankmongo.Service.AuditService#createAudit(com.training.
	 * bankmongo.Model.Audit)
	 */
	@Override
	public Audit createAudit(Audit audit) {

		Audit auditDummy = null;
		auditDummy = auditDAO.save(audit);
		return auditDummy;
	}

	@Override
	public Audit getAudit(String uuid) {
		Optional<Audit> auditDummy = null;
		Audit audit = null;
		auditDummy = auditDAO.findById(uuid);
		if (auditDummy.isPresent()) {
			audit = auditDummy.get();
			return audit;
		} else {
			return null;
		}
	}

	@Override
	public Audit deleteAudit(String uuid) {
		Optional<Audit> auditDummy = null;
		Audit audit = null;
		auditDummy = auditDAO.findById(uuid);
		if (auditDummy.isPresent()) {
			audit = auditDummy.get();
			auditDAO.delete(audit);
			return audit;
		} else {
			return null;
		}
	}

	@Override
	public Audit updateAudit(String uuid) {
		Optional<Audit> auditDummy = null;
		Audit audit = null;
		auditDummy = auditDAO.findById(uuid);
		if (auditDummy.isPresent()) {
			audit = auditDummy.get();
			audit.setEventName("customer");
			auditDAO.save(audit);
			return audit;
		} else {
			return null;
		}
	
	}

}
