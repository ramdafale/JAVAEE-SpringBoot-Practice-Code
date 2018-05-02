package com.training.groceryApp;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import model.Customer;
import model.Retailer;
import model.Supplier;
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

		JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);

		testAddCustomer = context.getBean("cdao", CustomerDAOImpl.class);
		testGoods = context.getBean("gdao", GoodsDAOImpl.class);

	}

	@Test
	public void checkForNull() {
		String retailerName = null;
		String retailerAddress = null;

		Retailer retailer = new Retailer("Spectre Litt", "NY");
		assertNotEquals("Enter valid Name", retailerName, retailer.getRetailerName());

		assertNotEquals("Enter valid address", retailerAddress, retailer.getRetailerAddres());
	}
	@Test
	public void checkForNullCustomer() {

		String customerId = null;
		String customerName = null;
		String customerAddress = null;
		String paymentMode = null;
		

		Customer customer = new Customer(1, "rama", "wardhaa", "debitcard");
		assertNotEquals("Enter valid id", customerId, customer.getCustomerId());
		assertNotEquals("Enter valid name", customerName, customer.getCustomerName());
		assertNotEquals("Enter valid address", customerAddress, customer.getCustomerAddress());
		assertNotEquals("Enter valid paymentMode", paymentMode, customer.getPaymentMode());
	}

	

	@Test
	public void checkForNullSupplier() {
	
	String supplierId = null;
	String supplierName = null;
	String supplierAddress = null;
	int quantityOrder = 0;
	int orderId = 0;
	double amount = 0;

	Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
	assertNotEquals("Enter valid id", supplierName, supplier.getSupplierId());
	assertNotEquals("Enter valid name", supplierAddress, supplier.getSupplierName());
	assertNotEquals("Enter valid quantity", quantityOrder, supplier.getQuantityOrder());
	assertNotEquals("Enter valid Id", orderId, supplier.getOrderId());
	assertNotEquals("Enter valid amount", amount, supplier.getAmount());
}
	/*
	 * @Test public void InsertCustomerIntoDatabase() {
	 * 
	 * Customer customer = new Customer(3, "null", "nagpur", "cash");
	 * 
	 * int inserted = testAddCustomer.addCustomer(customer); if (inserted > 0) { int
	 * i = 1; }
	 * 
	 * Assert.assertEquals(1, inserted); }
	 */

}
