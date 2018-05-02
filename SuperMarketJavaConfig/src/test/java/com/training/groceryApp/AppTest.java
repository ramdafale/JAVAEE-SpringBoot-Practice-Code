package com.training.groceryApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
	/*
	 * @Test public void addCustomerToDAtabase() { final long id = 0; final String
	 * name = null; final String address = null; final String payment = null; final
	 * String retailer = null;
	 * 
	 * final Customer customer = new Customer(7, "mukund", "melbern", "credit"); //
	 * assertNotEquals("Invalid id",id,customer.getCustomerId()); //
	 * assertNotEquals("Invalid name",name,customer.getCustomerName());
	 * 
	 * final long value = customerService.addCustomer(customer);
	 * 
	 * assertEquals("", 1, value);
	 * 
	 * }
	 */

	// Junit for deleting a customer
	/*
	 * @Test public void deleteCustomer() {
	 * 
	 * final long value = customerService.removeCusotmer(102); assertEquals("",1,
	 * value); }
	 */

	// Junit for updating Name of customer
	/*
	 * @Test public void updateCustomer() {
	 * 
	 * //customer.setPaymentMode("credit"); final long
	 * value=customerService.updateCustomer(2, "Ramesh"); assertEquals("",1, value);
	 * }
	 */

	// Junit for adding a goodIntoDB

	 // @Test
	   public void addGoods()
	   {
		   Goods goods=new Goods(1002,"kitkat",5,12.00);
		   long value=goodsService.addGoods(goods);
		   assertEquals("",1002,value);
	   }

	   
		// Junit for remove a good IntoDB
	   //@Test
	   public void removeGoods()
	   {
		   long value=goodsService.removeGoods(1001);
		   assertEquals(1,value);
	   }
	   
		// Junit for updating a goodIntoDB
	 //  @Test
	   public void updateGoods() {
		   long value=goodsService.updateGoods(1, "apple");
		   assertEquals(1,value);
	   }
	   
	   
	   
	   
	   // @Test
		public void viewCustomer()
		{
			
			final List<Customer> list=retailerService.viewCustomer("R2");
			assertThat( list,hasItems((new Customer(5,"sona","chennai","credit","R2")),new Customer(3,"sona","chennai","credit","R2")));
			
		}
		
//		@Test
		public void viewSupplier()
		{
			final List<Supplier> list=retailerService.viewSupplier("R1");
			assertThat(list,hasItems((new Supplier(103,"Sonam","Chennai",20,112,20,"R1")),(new Supplier(101,"Pooja","Chennai",20,112,40,"R1"))));
		}
		
		//@Test
		public void viewGoods()
		{
			final List<Goods> list=retailerService.viewGoods("R1");
			assertThat(list,hasItems(new Goods(1002,"kitkat",12,40,101)));
		}
		
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	// Checking Retailer Entity for null values
	@Test
	public void checkForNull() {
		final String retailerName = null;
		final String retailerAddress = null;

		final Retailer retailer = new Retailer("Spectre Litt", "NY");
		assertNotEquals("Enter valid Name", retailerName, retailer.getRetailerName());

		assertNotEquals("Enter valid address", retailerAddress, retailer.getRetailerAddres());
	}

	// Checking Customer Entity for null values
	@Test
	public void checkForNullCustomer() {

		final String customerId = null;
		final String customerName = null;
		final String customerAddress = null;
		final String paymentMode = null;

		final Customer customer = new Customer(1, "rama", "wardhaa", "debitcard");
		assertNotEquals("Enter valid id", customerId, customer.getCustomerId());
		assertNotEquals("Enter valid name", customerName, customer.getCustomerName());
		assertNotEquals("Enter valid address", customerAddress, customer.getCustomerAddress());
		assertNotEquals("Enter valid paymentMode", paymentMode, customer.getPaymentMode());
	}

	// Checking Supplier Entity for null values
	@Test
	public void checkForNullSupplier() {

		final int supplierId = 0;
		final String supplierName = null;
		final String supplierAddress = null;
		final int quantityOrder = 0;
		final int orderId = 0;
		final double amount = 0;

		Supplier supplier = new Supplier(1, "Jessica", "10th Street", 2, 1, 1990.00);
		System.out.println("supplier:" + supplier);
		assertNotEquals("Enter valid id", supplierId, supplier.getSupplierId());
		assertNotEquals("Enter valid name", supplierName, supplier.getSupplierName());
		assertNotEquals("Enter valid address", supplierAddress, supplier.getSupplierAddress());
		assertNotEquals("Enter valid quantity", quantityOrder, supplier.getQuantityOrder());
		assertNotEquals("Enter valid Id", orderId, supplier.getOrderId());
		assertNotEquals("Enter valid amount", amount, supplier.getAmount());
	}

}
