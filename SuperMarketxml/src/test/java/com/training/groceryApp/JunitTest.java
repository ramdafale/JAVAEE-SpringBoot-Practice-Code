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
	 /*  public void addGoods()
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
	   */
	   
	   
	   
	   // @Test
		/*public void viewCustomer()
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
		*/
	   
	   
	
	
	
	
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
