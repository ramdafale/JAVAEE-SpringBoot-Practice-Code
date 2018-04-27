package com.bankspring.spring3;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Account;
import model.Address;
import model.Bank;
import model.Contact;
import model.Customer;
import model.Statement;
import service.AccountImp;
import service.AccountService;

public class JunitTest {

	AccountService accObj = new AccountImp();

	// Testing deposit amount method
	@Test
	public void checkDeposit() {
		
		final Address address1 = new Address("Nagpur Road", "100", "442001", "Wardha");
		final Contact contact1 = new Contact(976651485, 0715225, "ramdafale@gmail.com", address1);
		final Statement statement1 = new Statement("Saving account created Succesfully !!!");

		final List<Statement> statementList = new ArrayList<>();
		statementList.add(statement1);

		final Account acc = new Account("111", 0.07, 5555, 5, statementList, "SavingType");

		final List<Account> accountList = new ArrayList<>();
		accountList.add(acc);

		final Customer cust1 = new Customer("Ram", "Dafale", contact1, accountList, 1);
		List<Customer> list6 = new ArrayList<>();

		list6.add(cust1);

		Bank bank1 = new Bank("CapgBank", list6);
		
		
		accObj.deposit(cust1, "111", 1000);
		assertEquals("test case", 6555, acc.getBalance(), 3);

	}

	// Testing withdraw amount method
	@Test
	public void checkWithdraw() {
		final Address address2 = new Address("Wardha Road", "100", "442001", "Wardha");
		final Contact contact = new Contact(9865452, 9584615, "shyamdafale@gmail.com", address2);
		final Statement statement = new Statement("Saving account created Successfully");
		final List<Statement> list2 = new ArrayList<>();
		list2.add(statement);
		final Account acc = new Account("112", 0.07, 6000, 12, list2, "Saving");
		final List<Account> list3 = new ArrayList<>();
		list3.add(acc);
		final Customer cust1 = new Customer("", "Jais", contact, list3, 1);
		List<Customer> list6 = new ArrayList<>();
		list6.add(cust1);
		Bank bank1 = new Bank("CapgBank", list6);

		accObj.withdraw(cust1, "112", 3000);
		assertEquals("test case", 3000, acc.getBalance(), 3);

	}

	// Update Mobile Number
	@Test
	public void checkUpdate() {
		final Address address1 = new Address("Nagpur Road", "100", "442001", "Wardha");
		final Contact contact1 = new Contact(976651485, 0715225, "ramdafale@gmail.com", address1);
		final Statement statement1 = new Statement("Saving account created Succesfully !!!");

		final List<Statement> statementList = new ArrayList<>();
		statementList.add(statement1);

		final Account acc = new Account("111", 0.07, 5555, 5, statementList, "SavingType");

		final List<Account> accountList = new ArrayList<>();
		accountList.add(acc);

		final Customer cust1 = new Customer("Ram", "Dafale", contact1, accountList, 1);
		List<Customer> list6 = new ArrayList<>();

		list6.add(cust1);

		Bank bank1 = new Bank("CapgBank", list6);

		accObj.update(cust1, "112", 000001);
		assertEquals(000001, contact1.getMobileNumber());

	}

	// Delete My Account
	@Test
	public void checkDelete() {
		final Address address = new Address("Nagpur Road", "102", "442012", "Delhi");
		final Contact contact = new Contact(984515, 4575678, "mymail@gmail.com", address);
		final Statement stat = new Statement("Saving account created Succesfully !!!");
		final Statement stat1 = new Statement("Saving account created Succesfully !!!");
		final List<Statement> list2 = new ArrayList<>();
		final List<Statement> list4 = new ArrayList<>();
		list2.add(stat);
		list4.add(stat1);
		final Account acc = new Account("1", 11, 4000, 5, list2, "Saving");
		final Account acc1 = new Account("2", 12, 5000, 5, list4, "Saving");
		final List<Account> list3 = new ArrayList<>();
		list3.add(acc);
		list3.add(acc1);
		final Customer cust1 = new Customer("emaual", "kaif", contact, list3, 1);
		List<Customer> list6 = new ArrayList<>();
		list6.add(cust1);
		Bank bank1 = new Bank("CapgBank", list6);

		accObj.deleteAccount(cust1, "1");
		assertEquals("test case", 1, cust1.getAccunlist().size());

	}

}
