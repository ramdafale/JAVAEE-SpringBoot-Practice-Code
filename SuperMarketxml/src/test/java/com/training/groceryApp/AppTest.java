package com.training.groceryApp;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import model.Customer;
import model.Goods;
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

	@Test
	public void checkCustName() {
		final String customerName = null;
		final Customer customer = new Customer(1, "rama", "wardhaa", "debitcard");
		assertNotEquals("Enter valid name", customerName, customer.getCustomerName());
	}

	@Test
	public void checkCustAddress() {
		final String customerAddress = null;
		final Customer customer = new Customer(1, "rama", "wardhaa", "debitcard");
		assertNotEquals("Enter valid address", customerAddress, customer.getCustomerAddress());
	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplierId() {

		final int supplierId = 0;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		// System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid id", supplierId, supplier.getSupplierId());

	}

	public void checkForNullSupplierName() {

		final String supplierName = null;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		System.out.println("supplier:" + supplier);
		// assertNotEquals("Enter valid id", supplierId, supplier.getSupplierId());
		assertNotEquals("Enter valid name", supplierName, supplier.getSupplierName());

	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplierquantityOrder() {

		final int quantityOrder = 0;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		// System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid quantity", quantityOrder, supplier.getQuantityOrder());

	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplierAddress() {

		final String supplierAddress = null;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		// System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid address", supplierAddress, supplier.getSupplierAddress());

	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplierOrderID() {

		final int orderId = 0;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		// System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid Id", orderId, supplier.getOrderId());

	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplierAmount() {

		final double amount = 0;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		// System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid amount", amount, supplier.getAmount());

	}

	@Test
	public void checkforCustomerOneArg() {

		final String name = null;

		final Customer customer = new Customer("johan");
		assertNotEquals("Enter valid id", name, customer.getCustomerId());

		// System.out.println("supplier:" + supplier);

	}

	@Test
	public void checkforsupplierid() {
		long supplierId = 0;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid id", supplierId, supplier.getSupplierId());
	}

	@Test
	public void checkforsuppliername() {
		String supplierName = null;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid name", supplierName, supplier.getSupplierName());
	}

	@Test
	public void checkforsupplieraddress() {
		String supplierAddress = null;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid address", supplierAddress, supplier.getSupplierAddress());
	}

	@Test
	public void checkforquantityOrder() {
		long quantityOrder = 0;
		;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid order quantity", quantityOrder, supplier.getQuantityOrder());
	}

	@Test
	public void checkfororderId() {
		long orderId = 0;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid order id", orderId, supplier.getOrderId());
	}

	@Test
	public void checkforamount() {
		float amount = 0;
		Supplier supplier = new Supplier(706, "raman", "andheri", 10, 25, 2000);
		assertNotEquals("Invalid amount", amount, supplier.getAmount());
	}

	@Test
	public void testSetRetailerName() {
		Retailer rname = new Retailer();
		rname.setRetailerName("Thanos");
		assertTrue(rname.getRetailerName() == "Thanos");
	}

	@Test
	public void testSetRetailerAddress() {
		Retailer raddress = new Retailer();
		raddress.setRetailerName("Knowwhere");
		assertTrue(raddress.getRetailerAddres() == "Knowwhere");
	}

	@Test
	public void testGetAddress() {
		Retailer rname = new Retailer();
		rname.setRetailerAddres("wardhaa");
		assertTrue(rname.getRetailerAddres() == "wardhaa");
	}

	@Test
	public void testGetName() {
		Retailer rname = new Retailer();
		rname.setRetailerName("gamora");
		assertTrue(rname.getRetailerName() == "gamora");
	}

	@Test
	public void testSetGoodsId() {
		final Goods goods = new Goods();
		goods.setGoodsId(8);
		assertTrue(goods.getGoodsId() == 8);
	}

	@Test
	public void testSetGoodsName() {
		final Goods goods = new Goods();
		goods.setGoodsName("Thanos");
		assertTrue(goods.getGoodsName() == "Thanos");
	}

	@Test
	public void testsetGoodsQuantity() {
		final Goods goods = new Goods();
		goods.setGoodsQuantity(5);
		assertTrue(goods.getGoodsQuantity() == 5);
	}

	@Test
	public void testsetGoodsPrice() {
		final Goods goods = new Goods();
		goods.setGoodsPrice(552);
		assertTrue(goods.getGoodsPrice() == 552);
	}

	@Test
	public void testGetId() {
		final Goods goods = new Goods();
		goods.setGoodsId(1);
		assertTrue(goods.getGoodsId() == 1);
	}

	@Test
	public void testGetGoodsName() {
		final Goods goods = new Goods();
		goods.setGoodsName("ironman");
		assertTrue(goods.getGoodsName() == "ironman");
	}

	@Test
	public void testGetQuantity() {
		final Goods goods = new Goods();
		goods.setGoodsQuantity(8);
		assertTrue(goods.getGoodsQuantity() == 8);
	}

	@Test
	public void testGetPrice() {
		final Goods goods = new Goods();
		goods.setGoodsPrice(8);
		assertTrue(goods.getGoodsPrice() == 8);
	}

}
