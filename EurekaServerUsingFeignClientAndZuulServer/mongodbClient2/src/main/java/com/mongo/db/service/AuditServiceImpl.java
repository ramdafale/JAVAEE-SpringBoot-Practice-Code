package com.mongo.db.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.db.document.AuditLog;
import com.mongo.db.exception.AuditException;
import com.mongo.db.repo.AuditMongoRepository;

@Service("auditService")
public class AuditServiceImpl implements IAuditService{

	@Autowired
	private AuditMongoRepository auditRepo;
	@Override
	public AuditLog createAudit(AuditLog audit)throws AuditException {
		AuditLog audi= auditRepo.save(audit);
		if(audi!=null)
		{
		return audi;
		}
		else
		{
			throw new AuditException("audit not added");
		}
	}

	

	@Override
	public String deleteAudit(UUID id) throws AuditException {
		Optional<AuditLog> audit=auditRepo.findByeventID(id);
		if(audit.isPresent())
		{AuditLog aud=audit.get();
		 auditRepo.delete(aud);;
		return "deleted";
		}
		else
		{
			throw new AuditException("not deleted");
		}
	}

	@Override
	public AuditLog update(UUID id,String eventType) throws AuditException {
		//changes made after downgrading application
		Optional<AuditLog> audit=auditRepo.findByeventID(id);
		if(audit.isPresent())
		{
		AuditLog auditLog=audit.get();
		auditLog.setEventType(eventType);
		AuditLog auu=auditRepo.save(auditLog);
		return auu;
		}
		else
		{
			throw new AuditException("id not found!!!!");
		}
	}



	@Override
	public List<AuditLog> getDetails(String eventName) throws AuditException {
		// TODO Auto-generated method stub
		List<AuditLog> list=auditRepo.findByEventName(eventName);
		if(list.isEmpty())
		{
			throw new AuditException("List is empty");
		}
		else
		{
			return list;
		}
		
	}



	@Override
	public List<AuditLog> findall() throws AuditException {
		// TODO Auto-generated method stub
		return auditRepo.findAll();
	}

}
