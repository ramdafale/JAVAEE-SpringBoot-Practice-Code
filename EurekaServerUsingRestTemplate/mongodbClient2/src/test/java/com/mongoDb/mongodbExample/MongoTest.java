/*package com.mongoDb.mongodbExample;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mongo.db.document.AuditLog;
import com.mongo.db.document.BaseEntity;
import com.mongo.db.exception.AuditException;
import com.mongo.db.repo.AuditMongoRepository;
import com.mongo.db.service.AuditServiceImpl;
import com.mongo.db.service.IAuditService;

@RunWith(MockitoJUnitRunner.class)
public class MongoTest {
	
	@Mock
	private AuditMongoRepository auditRepo;
	
	
	@InjectMocks IAuditService auditServcie=new AuditServiceImpl();
	 Date date=new Date();
		UUID id;
		Timestamp time;
		BaseEntity base1;
		BaseEntity bsae2;
		AuditLog audit;
		AuditLog audit1;
		AuditLog audit2;
	@Before
	public void init()
	{
		  date=new Date();
		 id=UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		 time=new Timestamp(date.getTime());
		 base1=new BaseEntity("1");
		 bsae2=new BaseEntity("2");
		 audit =new AuditLog(id,"create","creation",time,"3",base1,bsae2);
		 audit1 =new AuditLog(UUID.fromString("38400000-8cf0-11cd-b23e-10b96e4ef00d"),"createnew","creation",time,"3",base1,bsae2);
	}
	@Test
	public void auditcreateTest()throws AuditException
	{ Date date=new Date();
		UUID id=UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		Timestamp time=new Timestamp(date.getTime());
		BaseEntity base1=new BaseEntity("1");
		BaseEntity bsae2=new BaseEntity("2");
		AuditLog audit =new AuditLog(id,"create","creation",time,"3",base1,bsae2);
		Mockito.when(auditRepo.save(audit)).thenReturn(audit);
		AuditLog audi;
		
			audi = auditServcie.createAudit(audit);
			assertEquals("created",audi,audit);
	}
    @Test(expected=AuditException.class)
    public void falsecreateTest() throws AuditException
    {
		AuditLog audit1=null;
		Mockito.when(auditRepo.save(audit1)).thenReturn(audit1);
		AuditLog audi;
	    audi = auditServcie.createAudit(audit1);
    }

@Test
public void auditgetDetails()throws AuditException
{
	
	
	List<AuditLog> list=new ArrayList();

	list.add(audit);
	list.add(audit1);
	Mockito.when(auditRepo.findByEventName("create")).thenReturn(list);
	List<AuditLog> newList=auditServcie.getDetails("create");
	assertEquals(list, newList);

}
@Test(expected=AuditException.class)
public void auditgetDetailsfalse()throws AuditException
{
	
	
	List<AuditLog> list=new ArrayList<>();
	Mockito.when(auditRepo.findByEventName("mongo")).thenReturn(list);
	List<AuditLog> newList=auditServcie.getDetails("mongo");
	

}

@Test
public void auditupdatetruecase() throws AuditException
{
	Optional<AuditLog> opt=Optional.of(audit);
	when(auditRepo.findById(id)).thenReturn(opt);
	AuditLog auditt=opt.get();
	when(auditRepo.save(auditt)).thenReturn(auditt);
	AuditLog newAudit=auditServcie.update(id, "updation");

	assertEquals(auditt,newAudit);
}

@Test(expected=AuditException.class)
public void auditupdatefalsecase() throws AuditException
{
	Optional<AuditLog> opt=Optional.empty();
	when(auditRepo.findById(id)).thenReturn(opt);
	//AuditLog auditt=opt.get();
	//when(auditRepo.save(auditt)).thenReturn(auditt);
	AuditLog newAudit=auditServcie.update(id, "updation");


}

@Test
public void auditdeletetruecase() throws AuditException
{
	Optional<AuditLog> opt=Optional.of(audit);
	when(auditRepo.findById(id)).thenReturn(opt);
	AuditLog auditt=opt.get();
	
	String newAudit=auditServcie.deleteAudit(id);

	assertEquals("deleted",newAudit);
}

}
*/