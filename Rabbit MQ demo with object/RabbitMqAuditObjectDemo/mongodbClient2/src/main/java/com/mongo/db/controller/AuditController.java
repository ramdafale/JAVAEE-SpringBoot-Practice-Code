package com.mongo.db.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.db.document.AuditLog;
import com.mongo.db.exception.AuditException;
import com.mongo.db.service.IAuditService;
import com.mongo.db.vo.updateAuditRequest;

/**
 * @author trainee
 *
 */
//@RequestMapping("/audit")
@RestController
public class AuditController {
	
	@Autowired
	private IAuditService auditService;
/**
 * 	
 * @param audit
 * @return
 */
	@PostMapping
	public ResponseEntity<?>create(@RequestBody final AuditLog audit)
	{
		try {
			auditService.createAudit(audit);
			return new ResponseEntity<AuditLog>(audit,HttpStatus.ACCEPTED);
		} catch (AuditException exep) {
			return new ResponseEntity<String>(exep.getMessage(),HttpStatus.OK);
		}
	}
	/**
	 * 
	 * @param eventName
	 * @return
	 */
	@GetMapping("/{eventName}")
	public ResponseEntity<?>getAudit(@PathVariable @NotNull final String eventName)
	{
		
		try {
			List<AuditLog> list=auditService.getDetails(eventName);
			return new ResponseEntity<List<AuditLog>>(list,HttpStatus.OK);
		} catch (AuditException e) {
			
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?>getAllAudit()
	{
		
		try {
			List<AuditLog> list=auditService.findall();
			return new ResponseEntity<List<AuditLog>>(list,HttpStatus.OK);
		} catch (AuditException e) {
			
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * @param update
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?>updateAudit(@RequestBody final updateAuditRequest update)
	{
	
		try {
			AuditLog audit=auditService.update(update.getEventId(), update.getEventType());
			return new ResponseEntity<AuditLog>(audit,HttpStatus.ACCEPTED);
		} catch (AuditException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	/**
	 * 
	 * @param eventId
	 * @return
	 */
	@DeleteMapping("{eventId}")
	public ResponseEntity<?>deleteAudit(@PathVariable final UUID eventId)
	{
	
		try {
			
			String audit=auditService.deleteAudit(eventId);
			return new ResponseEntity<String>(audit,HttpStatus.ACCEPTED);
		} catch (AuditException e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
}
