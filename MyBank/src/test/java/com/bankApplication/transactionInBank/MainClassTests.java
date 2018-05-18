package com.bankApplication.transactionInBank;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.example.bank.TransactionInBankApplication;
import com.example.bank.controller.BankController;
import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.CustomerServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TransactionInBankApplication.class})
public class MainClassTests {
	
	
/*	@LocalServerPort
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
	*/
	
	    /*
	
	    private MockMvc mockMvc;

	    @Mock
	    private CustomerServiceImpl userService;

	    @InjectMocks
	    private BankController userController;
	    
	    
	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(userController)
	               
	                .build();
	    }
	    
	    private  RestTemplate restTemplate;
	    
	    public DetailsServiceClient(RestTemplateBuilder restTemplateBuilder) {
	        restTemplate = restTemplateBuilder.build();
	    }
	 
	    public Customer getUserDetails(String name) {
	        return restTemplate.getForObject("/{name}/details",
	        		Customer.class, name);
	    }
	*/
	

}
