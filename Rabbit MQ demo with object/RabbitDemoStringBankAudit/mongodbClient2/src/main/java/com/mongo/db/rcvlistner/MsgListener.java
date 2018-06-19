package com.mongo.db.rcvlistner;

import org.springframework.stereotype.Component;

import com.mongo.db.document.AuditLog;







@Component
public class MsgListener {

	/*@Autowired
	AuditMongoRepository amd;
	*/
	 public void receiveMessage(String message) {
	       System.out.println("Received <" + message + ">");
	      // AuditLog auditNew = new AuditLog(message.getEventID(), message.getEventName(), message.getEventType(),message.getEventDate(),message.getUserId(), message.getOldValue(), message.getNewValue());	       
	       //amd.save(auditNew);
	    }
}