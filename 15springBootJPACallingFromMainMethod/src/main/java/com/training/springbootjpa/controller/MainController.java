package com.training.springbootjpa.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;
import com.training.springbootjpa.model.Goods;
import com.training.springbootjpa.model.Retailer;
import com.training.springbootjpa.model.Supplier;
import com.training.springbootjpa.repository.CustomerDAO;
import com.training.springbootjpa.service.CustomerService;
import com.training.springbootjpa.service.GoodsService;
import com.training.springbootjpa.service.RetailerService;
import com.training.springbootjpa.service.SupplierService;

@RestController
public class MainController {

	
	
	private static final Logger LOGGER = Logger.getLogger( MainController.class.getName() );
	/*
	 * with help of this annonation we can access all the proporties of bean we can
	 * access all method of CustomerService.
	 */
	@Autowired
	private CustomerService customerService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private RetailerService retailerService;

	@Autowired
	CustomerDAO cdao;

	/*
	 * Get Customer Details using Id Handling the exception using Custom Exception
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws ManagedException {
		LOGGER.info("Inside Get Customer By Id");
		Customer customer = null;

		try {
			customer = customerService.getCustomerDetail(id);
		} catch (ManagedException e) {

			e.getMessage();
		}
		System.out.println("Customer response >>>>" + customer);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Get Customer Details using Name
	// Handling the exception
	/*
	 * Also we can send your whole Customer Details to UI using
	 * ResponseEntity<Customer> For now this method is just telling user that your
	 * data is being get added succesfully.
	 * 
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/searchCustomer/{customerName}")

	public ResponseEntity searchCustomer(@PathVariable String customerName) throws ManagedException {
		LOGGER.info("Inside Get Customer By Name");
		
		Customer customerData = null;

		customerData = cdao.findByCustomerName(customerName);
		if (customerData == null)
			return new ResponseEntity("Data not found", HttpStatus.OK);
		else
			return new ResponseEntity("Data  found", (MultiValueMap) customerData, HttpStatus.OK);

	}

	/*this method shows all customers to admin if requested
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllCustomer")
	// @PostMapping(path = "/addCustomer")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		LOGGER.info("Inside Get All Customers ");
		final List<Customer> customerData;

		customerData = customerService.getCustomer();

		return new ResponseEntity(customerData, HttpStatus.OK);
	}

	
	/*
	 * This method add new customer to database 
	 * Exception Handling-  if users provides Null to Textbox then it will throw Proper Message to user .  
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addCustomer")
	// @PostMapping(path = "/addCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		System.out.println(customer.getCustomerName()+ customer.getPaymentMode() );
		LOGGER.info("Inside Add New Customer ");
		Customer customerData = null;

		try {
		
			customerData = customerService.addCustomer(customer);
			return new ResponseEntity(customerData, HttpStatus.OK);
			
		} catch (ManagedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity("Provide valid input", HttpStatus.OK);
		}

		
	}
	
	

	
	
	/**
	  * GET /delete  Delete a customer details from the database.
	  * 
	  * 
	  */
	 @RequestMapping("/delete/{customerId}")
	 public ResponseEntity<Customer> deleteCustomerRecord(@PathVariable Long customerId) {
		 
		 Customer cust =null;
			LOGGER.info("Inside Delete Customer ");
		 try {
			cust = 	customerService.deleteCustomerById(customerId);
		} catch (ManagedException e) {
			
			
			e.printStackTrace();
			return new ResponseEntity("Given Id not found", HttpStatus.OK);
		}
	   
		 return new ResponseEntity("Given Id deleted successfully ", HttpStatus.OK);
	 }
	
	 
	 /**
	  * 
	  * Another Implementation
	  * GET /delete  Delete a customer details from the database.
	  * 
	  * 
	  */


/**
	  * GET /delete  Delete a booking from the database.
	  */
	/* @RequestMapping("/delete/{customerId}")
	 public String deletecustomer(@PathVariable Long customerId) {
		 
			LOGGER.info("Inside Delete Customer ");
		 try {
			customerService.deleteCustomerById(customerId);
		} catch (ManagedException e) {
			
			
			e.printStackTrace();
			  return "CustomerID #"+customerId+" Not Found";
		}
	   
	     return "CustomerID #"+customerId+" deleted successfully";
	 }

*/

	 
	 
	 
	 
	 @PutMapping("/customerUpdate/{id}")
		public ResponseEntity<?> customerUpdate(@RequestBody final Customer customer,
				@PathVariable(value = "id") final Long id) {
			Long customerId;
			try {
				customerId = customerService.updateCustomer(customer, id);
				return new ResponseEntity<Long>(customerId, HttpStatus.OK);
			} catch (ManagedException e) {
				// TODO Auto-generated catch block
				String msg = e.getMessage();
				return new ResponseEntity<String>(msg, HttpStatus.OK);

			}

		}

	@RequestMapping(method = RequestMethod.POST, value = "/addGoods", produces = MediaType.APPLICATION_JSON_VALUE) 
	ResponseEntity<Goods> createGoods(@RequestBody Goods goods) {
		System.out.println(goods);
		final Goods goodsData;
		goodsData = goodsService.addGoods(goods);
		return new ResponseEntity(goodsData, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteGoods/{deleteById}", method = RequestMethod.GET)
	public ResponseEntity<Goods> deleteGoods(@PathVariable("deleteById") long deleteById) {
		final List goodsList;
		goodsList = goodsService.deleteGoodsById(deleteById);
		return new ResponseEntity(goodsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateGoods/{updateById}", method = RequestMethod.GET)
	public ResponseEntity<Goods> updateGoods(@PathVariable("updateById") long updateById) {
		final Goods goods = goodsService.updateGoodsById(updateById);
		return new ResponseEntity(goods, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addSupplier", produces =

	MediaType.APPLICATION_JSON_VALUE) // @PostMapping(path = "/addSupplier")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
		System.out.println(supplier);
		final Supplier supplierData;
		supplierData = supplierService.addSupplier(supplier);
		return new ResponseEntity(supplierData, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteSupplier/{deleteById}", method = RequestMethod.GET)
	public ResponseEntity<Supplier> deleteSupplier(@PathVariable("deleteById") long deleteById) {
		final List supplierList;
		supplierList = supplierService.deleteSupplierById(deleteById);
		return new ResponseEntity(supplierList, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateSupplier/{updateById}", method = RequestMethod.GET)
	public ResponseEntity<Supplier> updateSupplier(@PathVariable("updateById") long updateById) {
		final Supplier supplier = supplierService.updateSupplierById(updateById);
		return new ResponseEntity(supplier, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addRetailer", produces = MediaType.APPLICATION_JSON_VALUE) // @PostMapping(path
																														// =
																														// "/addRetailer")
	public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer retailer) {
		System.out.println(retailer);
		final Retailer retailerData;
		retailerData = retailerService.addRetailer(retailer);
		return new ResponseEntity(retailerData, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteRetailer/{deleteById}", method = RequestMethod.GET)
	public ResponseEntity<Retailer> deleteRetailer(@PathVariable("deleteById") long deleteById) {
		final List retailerList;
		retailerList = retailerService.deleteRetailerById(deleteById);
		return new ResponseEntity(retailerList, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateRetailer/{updateById}", method = RequestMethod.GET)
	public ResponseEntity<Retailer> updateRetailer(@PathVariable("updateById") long updateById) {
		final Retailer retailer = retailerService.updateRetailerById(updateById);
		return new ResponseEntity(retailer, HttpStatus.OK);
	}

}
