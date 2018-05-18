package com.bankApplication.transactionInBank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;


//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@RestClientTest(CustomerRepository.class)
@ContextConfiguration
public class MainClassTests {
	
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		
		System.out.println(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class));
		System.out.println("test");
//		
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
//		.contains("Hello World");

	}
	
	
	
	
	@After
	public void clearDb(){
		customerRepository.deleteAll();
		

	}
	
	
	
	
	
	
	
	 // private final String USER_NAME_ADAM = "Adam";
	    private final Long ACTIVE_STATUS = 1L;

	    @Autowired
	    private CustomerRepository customerRepository;

	    @Test
	    public void givenEmptyDBWhenFindOneByNameThenReturnEmptyOptional() {
	        Optional<Customer> foundUser = customerRepository.findById(ACTIVE_STATUS);

	        assertThat(foundUser.isPresent()).isEqualTo(false);
	    }
	
	
	
	
	    
	    @Test
	    public void createCustomer(){
	        long id = 2;
	    	//Bank b = new Bank();
	        Customer u = new Customer("Viz",(long) 442001);
	        customerRepository.save(u);

	        assertEquals(customerRepository.findById(id),u);

	    }
	
	
	    
	
	    @Test
	    
	    
	
	    
	
	
	
	

}
