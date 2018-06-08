/**
 * 
 */
package com.springboot.bank;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.springboot.bank.dto.WrapperBankCustomer;
import com.springboot.bank.model.Customer;
import com.springboot.bank.repository.CustomerDAO;
import com.springboot.bank.service.CustomerService;

/**
 * @author ram
 *
 */
public class CustomerServiceTest {

	@Mock
	private CustomerDAO daoMock;

	@InjectMocks
	private CustomerService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	  @Test
      public void testAddCustomer_returnsNewCustomer() {

     
      }

}
