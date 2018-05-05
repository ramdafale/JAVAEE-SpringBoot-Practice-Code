package com.training.groceryApp;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.AppJavaConfig;
import model.Customer;

import model.Goods;
import model.Retailer;
import model.Supplier;
import repository.CustomerDAOImpl;
import repository.GoodsDAOImpl;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.GoodsServiceImpl;
import service.RetailerService;
import service.RetailerServiceImpl;
import service.SupplierService;
import service.SupplierServiceImpl;

/**
 * Unit test for simple Application.
 */
public class AppTest {

	CustomerDAOImpl testAddCustomer;
	GoodsDAOImpl testGoods;

	final ApplicationContext context = new AnnotationConfigApplicationContext(AppJavaConfig.class);

	final GoodsServiceImpl goodsService = context.getBean("goodsService", GoodsServiceImpl.class);
	final CustomerService customerService = context.getBean("customerService", CustomerServiceImpl.class);
	final SupplierService supplierService = context.getBean("supplierService", SupplierServiceImpl.class);
	final RetailerService retailerService = context.getBean("retailerService", RetailerServiceImpl.class);

	// Testing Addcustomer method

	@Test
	public void addCustomerToDAtabase() {
		final long id = 0;

		final Customer customer = new Customer(11, "mukund", "melbern", "credit"); //
		assertNotEquals("Invalid id", id, customer.getCustomerId()); //

		long dummy = 1;
		final long value = customerService.addCustomer(customer);

		assertEquals("", dummy, value);

	}

	@Test
	public void addCustomerToDAtabase1() {
		final String name = null;
		final Customer customer = new Customer(19, "mukund", "melbern", "credit"); //

		assertNotEquals("Invalid name", name, customer.getCustomerName());
	}

	@Test
	public void addCustomerToDAtabase2() {
		final String address = null;
		final Customer customer = new Customer(19, "mukund", "melbern", "credit"); //

		assertNotEquals("Invalid name", address, customer.getCustomerName());
	}

	@Test
	public void addCustomerToDAtabase3() {
		final String payment = null;
		final Customer customer = new Customer(19, "mukund", "melbern", "credit");
		assertNotEquals("Invalid name", payment, customer.getCustomerName());
	}

	// Junit for deleting a customer

	@Test
	public void deleteCustomer() {
			long dummy = 1;
		final long value = customerService.removeCusotmer(102);
		assertEquals("", dummy, value);
	}

	// Junit for updating Name of customer

	@Test
	public void updateCustomer() {

		// customer.setPaymentMode("credit");
		final long value = customerService.updateCustomer(2, "Ramesh");
		assertEquals("", 1, value);
	}

	// Junit for adding a goodIntoDB

	@Test
	public void addGoods() {
		Goods goods = new Goods(1002, "kitkat", 5, 12.00);
		long value = goodsService.addGoods(goods);
		assertEquals("", 1002, value);
	}

	// Junit for remove a good IntoDB
	@Test
	public void removeGoods() {
		long value = goodsService.removeGoods(1001);

		assertEquals(1, value);
	}

	// Junit for updating a goodIntoDB
	@Test
	public void updateGoods() {
		long value = goodsService.updateGoods(1, "apple");

		assertEquals("", 1, value);
	}

	@Test
	public void viewCustomer() {

		final List<Customer> list = retailerService.viewCustomer(2);
		assertThat(list,
				hasItems((new Customer(5, "rohit", "mumbai", "credit")), new Customer(3, "sona", "chennai", "credit")));

	}

	@Test
	public void viewSupplier() {
		final List<Supplier> list = retailerService.viewSupplier(1);
		assertThat(list, hasItems((new Supplier(103, "Rahul", "nagpur", 20, 112, 20)),
				(new Supplier(101, "kiran", "Newyork", 20, 112, 40))));
	}

	@Test
	public void viewGoods() {
		final List<Goods> list = retailerService.viewGoods();
		assertThat(list, hasItems(new Goods(1002, "kitkat", 12, 40)));
	}

	// Checking Retailer Entity for null values
	@Test
	public void checkForNull() {
		final String retailerName = null;
		final String retailerAddress = null;

		final Retailer retailer = new Retailer("Spectre Litt", "NY");
		assertNotEquals("Enter valid Name", retailerName, retailer.getRetailerName());

		// assertNotEquals("Enter valid address", retailerAddress,
		// retailer.getRetailerAddres());
	}

	public void checkForNullAddress() {
		// final String retailerName = null;
		final String retailerAddress = null;

		final Retailer retailer = new Retailer("Spectre Litt", "NY");
		// assertNotEquals("Enter valid Name", retailerName,
		// retailer.getRetailerName());

		assertNotEquals("Enter valid address", retailerAddress, retailer.getRetailerAddres());
	}

	// Checking Customer Entity for null values
	@Test
	public void checkForNullCustomer() {

		final String customerId = null;

		final String paymentMode = null;

		final Customer customer = new Customer(1, "rama", "wardhaa", "debitcard");
		assertNotEquals("Enter valid id", customerId, customer.getCustomerId());

		assertNotEquals("Enter valid paymentMode", paymentMode, customer.getPaymentMode());
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
	public void checkforsupplierid()
	{
		long supplierId=0;
		Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid id",supplierId,supplier.getSupplierId());
	}
	
	@Test
	public void checkforsuppliername()
	{
		String supplierName=null;
		Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid name",supplierName,supplier.getSupplierName());
	}
	@Test
	public void checkforsupplieraddress()
	{
		String supplierAddress=null;
		Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid address",supplierAddress,supplier.getSupplierAddress());
	}
	
	@Test
	public void checkforquantityOrder()
	{
		long quantityOrder=0;;
		Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid order quantity",quantityOrder,supplier.getQuantityOrder());
	}
	@Test
	public void checkfororderId()
	{
	    long orderId=0;
	    Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid order id",orderId,supplier.getOrderId());
	}
	
	@Test
	public void checkforamount()
	{
		float amount=0;
		Supplier supplier=new Supplier(706,"raman","andheri",10,25,2000);
		assertNotEquals("Invalid amount",amount,supplier.getAmount());
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
	    public void testGetAddress(){
		  Retailer rname = new Retailer();
		  rname.setRetailerAddres("wardhaa");
	        assertTrue(rname.getRetailerAddres() == "wardhaa");
	    }
	  
	

	  @Test
	    public void testGetName(){
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
