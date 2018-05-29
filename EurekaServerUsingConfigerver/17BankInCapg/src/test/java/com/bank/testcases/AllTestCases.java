/*package com.bank.testcases;






import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.bank.Exception.ManagedException;
import com.bank.controller.CustomerController;
import com.bank.dto.ATMReq;
import com.bank.dto.CreateCustomerRequest;
import com.bank.model.ATM;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.repository.ATMRepository;
import com.bank.repository.AccountRepository;
import com.bank.repository.BankRepository;
import com.bank.repository.CustomerRepository;
import com.bank.service.ATMServiceImpl;
import com.bank.service.AccountServiceImpl;
import com.bank.service.BankServiceImpl;
import com.bank.service.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@ComponentScan("application.properties")
public class AllTestCases {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@Autowired
	BankRepository bankRepository;

	@Autowired
	BankServiceImpl bankServiceImpl;
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Autowired
	ATMRepository atmRepository;

	@Autowired
	ATMServiceImpl atmServiceImpl;
	
	
	@Test
	public void createBank() throws ManagedException {
		final Bank bank = new Bank(1L, new BigDecimal(0));
		when(bankRepository.save(bank)).thenReturn(bank);
		// System.out.println(bank);
		assertThat(bankServiceImpl.createBank(bank), is(notNullValue()));
	}

	@Test
	public void testAddBank() throws ManagedException {
		final Bank bank = new Bank(1L, new BigDecimal(0));
		bankServiceImpl.createBank(bank);

		List<Bank> departments = bankServiceImpl.getBankDetails();
		Assert.assertEquals(1, departments.size());
	}

	@Test
	public void testAddCustomer() throws ManagedException {

		Customer employee = new Customer();
		employee.setCustomerName("Lokesh");
		employee.setPin(442001L);

		customerServiceImpl.createCustomer(employee);

		List<Customer> employees = (List<Customer>) customerServiceImpl.getCustomerDetails(1L);
		Assert.assertEquals(1, employees.size());
	}

	
	@Test
	public void testAddCustomerName() throws ManagedException {

		Customer employee = new Customer();
		employee.setCustomerName("Lokesh");
		employee.setPin(442001L);

		customerServiceImpl.createCustomer(employee);

		Customer employees =  customerServiceImpl.getCustomerDetails(1L);
		 Assert.assertEquals(employee.getCustomerName(), employees.getCustomerName());
	}
	RestTemplate restTemplate = new RestTemplate();
	@Autowired
	CustomerController customerController;
	
	
	
	@Test
	public void customerCreateRestTemplateTest() {
		Customer customer = new Customer();
		customer.setCustomerName("Lokesh");
		customer.setPin(442001L);
		
		final String uri = "http://localhost:8080/customer/createCustomer";
		Optional<Bank> ob = bankRepository.findById(1L);
		Bank obj = ob.get();

		CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest(customer,1L);
		String result = restTemplate.postForObject(uri, createCustomerRequest, String.class);

		//System.out.println(result);
		assertEquals(result, customerController.createCustomer(createCustomerRequest));
	}
	
	

	@Test
	public void viewCustByRestTemplateTest() {

		final String uri = "http://localhost:8080/getCustomer";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
		assertEquals(result, customerController.getCustomer(1L));
	}
	
	
	
	@Test
	public void testAddCustomerpin() throws ManagedException {

		Customer employee = new Customer();
		employee.setCustomerName("Lokesh");
		employee.setPin(442001L);

		customerServiceImpl.createCustomer(employee);

		Customer employees =  customerServiceImpl.getCustomerDetails(1L);
		 Assert.assertEquals(employee.getPin(), employees.getPin());
	}
	
	
	@Test
	public void testAddAccount() throws ManagedException {

		Account account = new Account (1L,new BigDecimal(100),new Customer(),new Bank(1L,new BigDecimal(0)));
	

		accountServiceImpl.createAccount(account);

		List<Account> information = (List<Account>) accountServiceImpl.getAccountDetails(1L);
		Assert.assertEquals(1, information.size());
	}

	@Test
	public void testAddAccountAmount() throws ManagedException {

		Account account = new Account (1L,new BigDecimal(100),new Customer(),new Bank(1L,new BigDecimal(0)));
	

		accountServiceImpl.createAccount(account);

		Account information = accountServiceImpl.getAccountDetails(1L);
		Assert.assertEquals(account.getAmount(), information.getAmount());
	}

	@Test
	public void testAddAccountId() throws ManagedException {

		Account account = new Account (1L,new BigDecimal(100),new Customer(),new Bank(1L,new BigDecimal(0)));
	

		accountServiceImpl.createAccount(account);

		Account information = accountServiceImpl.getAccountDetails(1L);
		Assert.assertEquals(account.getAccountId(), information.getAccountId());
	}


	@Test
	public void createAtm() throws ManagedException {
		Bank bank = new Bank(1L,new BigDecimal(1200));
		
		Optional<Bank> bnk = Optional.of(bank);

		ATM atm = new ATM(1L,new BigDecimal(5000), 1L);

		//ATMReq req = new ATMReq(1L,atm);
		
		
		when(bankRepository.findById(Mockito.any(Long.class))).thenReturn(bnk);
		when(atmRepository.save(atm)).thenReturn(atm);
		assertThat(atmServiceImpl.createATM(atm), is(notNullValue()));
 
	}
	
	
}*/