package com.training.springbootjpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.json.HTTP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.training.springbootjpa.controller.MainController;
import com.training.springbootjpa.model.Customer;

@SpringBootApplication
public class SpringbootjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
		
		final Logger LOGGER = Logger.getLogger( MainController.class.getName() );
		System.out.println("In main class dfirst");
		//getCustomerById();
		System.out.println("In main class");
		
		//createCustomer();
		
		//deleteEmployee();
		
		customerUpdate();
		
		
		
		
		
	}
		
	private static  void getCustomerById()
		{
		final Logger LOGGER = Logger.getLogger( MainController.class.getName() );
		    final String uri = "http://localhost:8080/customer/{customerId}";
		     
		    Map<String, Long> params = new HashMap<String, Long>();
		    params.put("customerId",  1L);
		     
		    RestTemplate restTemplate = new RestTemplate();
		    Customer result = restTemplate.getForObject(uri, Customer.class, params);
		     
		  //  LOGGER.info( result);
		    
		    System.out.println(result.toString());
		}
		
	
	private static  void createCustomer()
		{
		final Logger LOGGER = Logger.getLogger( MainController.class.getName() );
		    final String uri = "http://localhost:8080/addCustomer";
		    System.out.println("In Add customer MEthod");
		   // List<Customer> list = new ArrayList<Customer>();
		    
		    Customer cust = new Customer("Rama","SA","DEBIT");
		  
		    RestTemplate restTemplate = new RestTemplate();
		    Customer result = restTemplate.postForObject(uri, cust,Customer.class);
		    System.out.println("In Add customer adding");
		  //  LOGGER.info( result);
		    System.out.println(result);
		    //System.out.println(result.toString());
		}
	
	
	
	private static void deleteEmployee()
	{
	    final String uri = "http://localhost:8080/delete/{customerId}";
	     
	    Map<String, Long> params = new HashMap<String, Long>();
	    params.put("customerId", 2L);
	     
	  //  Customer updatedEmployee = new Customer("Marvel", "NewYork", "CASH");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put ( uri, Customer.class, params);
	}
	
	
	
	private static void customerUpdate()
	{
	    final String uri = "http://localhost:8080/customerUpdate/{id}";
	     
	    Map<String, Long> params = new HashMap<String, Long>();
	    params.put("id", 5L);
	     
	    Customer updatedEmployee = new Customer("Captain Marvel", "NewYork", "CASH");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.put ( uri, updatedEmployee, params);
	}
	
	
	
}
	
	
	

