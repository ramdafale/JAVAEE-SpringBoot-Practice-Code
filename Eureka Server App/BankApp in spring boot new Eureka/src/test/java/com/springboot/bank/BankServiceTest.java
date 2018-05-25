/**
 * 
 *//*
package com.springboot.bank;

*//**
 * @author ram
 *
 *//*

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.springboot.bank.controller.BankController;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;
import com.springboot.bank.service.BankService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankServiceTest {

	@Mock
	BankService bankService;

	@InjectMocks
	BankController bankController;

	@Test
	public void trueCheckCreateBank() throws BankException {
		final Bank bank = new Bank(1L, new BigDecimal(1000));
		when(bankService.createBank(bank)).thenReturn(bank);
		assertThat(bankController.addBank(bank), is(notNullValue()));
	}

	@Test
	public void falseCheckCreateBank() throws BankException {
		Bank bank = new Bank(-1L, new BigDecimal(2000));
		try {
			when(bankService.createBank(bank)).thenReturn(bank);
		} catch (BankException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResponseEntity<Bank> bankDummy = null;
		try {
			bankDummy = bankController.addBank(bank);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bankDummy);
		assertEquals("Bank not found", bankDummy.getBody(), bank);
	}
}
*/