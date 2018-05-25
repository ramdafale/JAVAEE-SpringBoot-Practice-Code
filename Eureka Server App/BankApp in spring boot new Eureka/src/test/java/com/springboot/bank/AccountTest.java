/*package com.springboot.bank;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Account;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.Customer;
import com.springboot.bank.repository.AccountDAO;
import com.springboot.bank.repository.BankDAO;
import com.springboot.bank.repository.CustomerDAO;
import com.springboot.bank.service.AccountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {

	@Mock
	private AccountDAO accRepo;

	@Mock
	private CustomerDAO custRepo;

	@Mock
	private BankDAO bankRepo;

	@InjectMocks
	private AccountServiceImpl accSer;

	@Test
	public void createAccount() {
		Bank bank = new Bank(new BigDecimal(1200));
		bank.setBankId(1L);
		Optional<Bank> bnk = Optional.of(bank);
		Customer customer = new Customer("ram", 2131, bank);
		customer.setCustomerId(4L);
		Optional<Customer> cust = Optional.of(customer);

		Account account = new Account(customer, new BigDecimal(4000), bank);
		AccountRequest accReq = new AccountRequest(1, 4, account);

		when(bankRepo.findById(Mockito.any(Integer.class))).thenReturn(bnk);

		when(custRepo.findById(Mockito.any(Integer.class))).thenReturn(cust);

		when(accRepo.save(account)).thenReturn(account);
		assertThat(accSer.createAccount(accReq), is(notNullValue()));

	}

	@Test(expected = BankException.class)
	
	public void checkAccount() {
		Bank bank = new Bank(new BigDecimal(1200));
		//bank.setId(1);
		//Optional<Bank> bnk = Optional.of(bank);
		final Optional<Bank> banks = Optional.empty();

		Customer customer = new Customer("rama", 2131, bank);
		customer.setCustomerId(4);

		final Optional<Customer> customers = Optional.empty();

		Account account = new Account(customer, new BigDecimal(4000), bank);
		AccountRequest accReq = new AccountRequest(1, 4, account);

		when(bankRepo.findById(Mockito.any(Integer.class))).thenReturn(banks);
		when(custRepo.findById(Mockito.any(Integer.class))).thenReturn(customers);

		 when(accRepo.save(account)).thenReturn(account);
		accSer.createAccount(accReq);

	}
	
	@Test
	public void deposit() {
		Bank bank = new Bank(new BigDecimal(1200));
		bank.setId(1);
		Optional<Bank> bnk = Optional.of(bank);
		
		Customer customer = new Customer("ramesh", 2131, bank);
		customer.setCustomerId(4);
		Optional<Customer> cust = Optional.of(customer);
		final Account account=new Account(customer,new BigDecimal(100),bank);
		account.setAccountId(2);
		final Optional<Account> acc=Optional.of(account);
		when(accRepo.findById(2)).thenReturn(acc);
		
		final Bank bank=new Bank(new BigDecimal(100));
		bank.setId(1);
		final Optional<Bank>banks=Optional.of(bank);
		when(bankRepo.findById(1)).thenReturn(banks);
		
		//final Account accounts=new Account(customer,new BigDecimal(200),bank);
		when(accRepo.save(Mockito.<Account>any())).thenReturn(account);
		
		//assertThat(accSer.depositMoney(2,new BigDecimal(100)), is(notNullValue()));
		
		assertEquals(account,accSer.depositMoney(1, new BigDecimal(20)));
	
	}
	 
	@Test
	public void viewAccount() {
		final Bank bank = new Bank(new BigDecimal(0));
		bank.setId(1);
		Optional<Bank> bnk = Optional.of(bank);
		final Customer customer = new Customer("rahul", 1231, bank);
		customer.setCustomerId(2);
		Optional<Customer> cust = Optional.of(customer);
		final Account account=new Account(customer,new BigDecimal(100),bank);
		account.setAccountId(3);
		final Optional<Account> acc=Optional.of(account);
		when(accRepo.findById(3)).thenReturn(acc);

		assertThat(accSer.getAccountDetailsById(account), is(notNullValue()));

	}
}
*/