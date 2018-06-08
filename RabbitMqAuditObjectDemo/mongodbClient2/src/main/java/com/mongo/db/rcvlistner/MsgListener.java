package com.mongo.db.rcvlistner;

import java.io.IOException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.db.document.AuditLog;
import auditdemo.demoAudit.entity.AuditLogExternal;

@Component
public class MsgListener {

	/*
	 * @Autowired AuditMongoRepository amd;
	 */
	public static Object fromJsonToJava(String json, Class type)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.readValue(json, type);
	}

	public void receiveMessage(String message) {
		AuditLogExternal value = null;

		try {
			System.out.println("Message Number " + MsgListener.fromJsonToJava(message, AuditLog.class) + " received.");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
