package com.training.bankmongo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.training.bankmongo.Model.Audit;
import com.training.bankmongo.Repository.AuditDAO;
import com.training.bankmongo.Service.AuditServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankmongoApplicationTests {

	

	RestTemplate rest1=new RestTemplate();
	
	
	@Mock
	AuditDAO auditDAO;
	@InjectMocks
	AuditServiceImpl auditServiceImpl;
	
	@Test
	public void createAudit() {
		Audit audit=new Audit( "Technical", "Spartans","Fun", "12-11-2018", "1", "2");

		when(auditDAO.save(audit)).thenReturn(audit);
		assertThat(auditServiceImpl.createAudit(audit), is(notNullValue()));
	}
	
	@Test
	public void viewAudit() {
		Audit audit=new Audit( "Technical", "Spartans","Fun", "12-11-2018", "1", "2");
		//audit.setEventName("NonTechnical");
		Optional<Audit> auditlog = Optional.of(audit);

		when(auditDAO.findById("1")).thenReturn(auditlog);

		assertThat(auditServiceImpl.getAudit("1"), is(notNullValue()));
	} 
	

	@Test
	public void auditupdatetruecase() 
	{
		Audit audit=new Audit( "Technical", "Spartans","Fun", "12-11-2018", "1", "2");
		Optional<Audit> opt=Optional.of(audit);
		when(auditDAO.findById("1")).thenReturn(opt);
		Audit auditt=opt.get();
		when(auditDAO.save(auditt)).thenReturn(auditt);
		Audit newAudit=auditServiceImpl.updateAudit("updation");

		assertEquals(auditt,newAudit);
	}
	
	@Test
	public void viewbyname()
	{
		String str="ramesh";
		String url="http://localhost:8080/viewByName/"+str;
		Audit aud=rest1.getForObject(url, Audit.class);
		assertEquals(str, aud.getEventName());
	}

	
	  @Test
	    public void falsecreateTest()
	    {
			Audit audit1=null;
			Mockito.when(auditDAO.save(audit1)).thenReturn(audit1);
			Audit audi;
		    audi = auditServiceImpl.createAudit(audit1);
	    }
	
	@Test
	public void updateAudit() {
		Audit audit=new Audit( "Technical", "Spartans","Fun", "12-11-2018", "1", "2");
		audit.setEventName("NonTechnical");
		Optional<Audit> auditlog = Optional.of(audit);

		when(auditDAO.findById("1")).thenReturn(auditlog);

		assertThat(auditServiceImpl.updateAudit("1"), is(notNullValue()));
	} 
	
	
	
	
	
	@Test
	public void deleteAudit() {
		Audit audit=new Audit( "Technical", "Spartans","Fun", "12-11-2018", "1", "2");
		//audit.setEventName("NonTechnical");
		Optional<Audit> auditlog = Optional.of(audit);

		when(auditDAO.findById("1")).thenReturn(auditlog);

		assertThat(auditServiceImpl.deleteAudit("1"), is(notNullValue()));
	} 
	
	
	
	
}
