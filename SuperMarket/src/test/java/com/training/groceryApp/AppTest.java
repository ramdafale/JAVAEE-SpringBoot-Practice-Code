package com.training.groceryApp;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import model.Customer;
import repository.CustomerDAOImpl;
import repository.GoodsDAOImpl;

/**
 * Unit test for simple Application.
 */
public class AppTest {

	CustomerDAOImpl testAddCustomer;
	GoodsDAOImpl testGoods;
	
	@Before
	public void LoadXml() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("dataSource");

		DriverManagerDataSource ds = context.getBean("dataSource", DriverManagerDataSource.class);

		JdbcTemplate jt =  context.getBean("jdbcTemplate",JdbcTemplate.class);

		testAddCustomer =  context.getBean("cdao",CustomerDAOImpl.class);
		testGoods =  context.getBean("gdao",GoodsDAOImpl.class);
		
	}


	@Test
	public void InsertCustomerIntoDatabase() {

		Customer customer = new Customer(3, "ganesh1", "nagpur", "cash");

		int inserted = testAddCustomer.addCustomer(customer);
		if (inserted > 0) {
			int i = 1;
		}

		Assert.assertEquals(1, inserted);
	}

}
