package com.training.springbootjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;
import com.training.springbootjpa.repository.CustomerDAO;
import com.training.springbootjpa.service.CustomerService;
import com.training.springbootjpa.service.GoodsService;
import com.training.springbootjpa.service.RetailerService;
import com.training.springbootjpa.service.SupplierService;

@RestController
public class MainController {

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

	// Get Customer Details using Id

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id) throws ManagedException {

		Optional<Customer> customer = null;

		try {
			customer = customerService.getCustomerDetail(id);
		} catch (ManagedException e) {
			
			e.printStackTrace();
		}
		System.out.println(customer);
	
			return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.OK);
		}

	

	//

	@RequestMapping(method = RequestMethod.GET, value = "/searchCustomer/{customerName}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @PostMapping(path = "/addCustomer")
	public List<Customer> searchCustomer(String customerName) {
		// final Customer customerData;
		List<Customer> customerData = cdao.findByCustomerName(customerName);
		return customerData;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllCustomer")
	// @PostMapping(path = "/addCustomer")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		final List<Customer> customerData;
		customerData = customerService.getCustomer();

		return new ResponseEntity(customerData, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	// @PostMapping(path = "/addCustomer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		final Customer customerData;
		customerData = customerService.addCustomer(customer);
		return new ResponseEntity(customerData, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCustomer/{deleteById}", method = RequestMethod.GET)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("deleteById") long deleteById) {
		final List<Customer> customerList;
		customerList = customerService.deleteCustomerById(deleteById);
		return new ResponseEntity(customerList, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateCustomer/{updateById}", method = RequestMethod.GET)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("updateById") long updateById) {
		final Customer customer = customerService.updateCustomerById(updateById);
		return new ResponseEntity(customer, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/addGoods", produces =
	 * 
	 * MediaType.APPLICATION_JSON_VALUE) // @PostMapping(path = "/addGoods") public
	 * ResponseEntity<Goods> createGoods(@RequestBody Goods goods) {
	 * System.out.println(goods); final Goods goodsData; goodsData =
	 * goodsService.addGoods(goods); return new ResponseEntity(goodsData,
	 * HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/deleteGoods/{deleteById}", method =
	 * RequestMethod.GET) public ResponseEntity<Goods>
	 * deleteGoods(@PathVariable("deleteById") long deleteById) { final List
	 * goodsList; goodsList = goodsService.deleteGoodsById(deleteById); return new
	 * ResponseEntity(goodsList, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/updateGoods/{updateById}", method =
	 * RequestMethod.GET) public ResponseEntity<Goods>
	 * updateGoods(@PathVariable("updateById") long updateById) { final Goods goods
	 * = goodsService.updateGoodsById(updateById); return new ResponseEntity(goods,
	 * HttpStatus.OK); }
	 * 
	 * @RequestMapping(method = RequestMethod.POST, value = "/addSupplier", produces
	 * =
	 * 
	 * MediaType.APPLICATION_JSON_VALUE) // @PostMapping(path = "/addSupplier")
	 * public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier
	 * supplier) { System.out.println(supplier); final Supplier supplierData;
	 * supplierData = supplierService.addSupplier(supplier); return new
	 * ResponseEntity(supplierData, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/deleteSupplier/{deleteById}", method =
	 * RequestMethod.GET) public ResponseEntity<Supplier>
	 * deleteSupplier(@PathVariable("deleteById") long deleteById) { final List
	 * supplierList; supplierList = supplierService.deleteSupplierById(deleteById);
	 * return new ResponseEntity(supplierList, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/updateSupplier/{updateById}", method =
	 * RequestMethod.GET) public ResponseEntity<Supplier>
	 * updateSupplier(@PathVariable("updateById") long updateById) { final Supplier
	 * supplier = supplierService.updateSupplierById(updateById); return new
	 * ResponseEntity(supplier, HttpStatus.OK); }
	 * 
	 * @RequestMapping(method = RequestMethod.POST, value = "/addRetailer", produces
	 * = MediaType.APPLICATION_JSON_VALUE) // @PostMapping(path = "/addRetailer")
	 * public ResponseEntity<Retailer> createRetailer(@RequestBody Retailer
	 * retailer) { System.out.println(retailer); final Retailer retailerData;
	 * retailerData = retailerService.addRetailer(retailer); return new
	 * ResponseEntity(retailerData, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/deleteRetailer/{deleteById}", method =
	 * RequestMethod.GET) public ResponseEntity<Retailer>
	 * deleteRetailer(@PathVariable("deleteById") long deleteById) { final List
	 * retailerList; retailerList = retailerService.deleteRetailerById(deleteById);
	 * return new ResponseEntity(retailerList, HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/updateRetailer/{updateById}", method =
	 * RequestMethod.GET) public ResponseEntity<Retailer>
	 * updateRetailer(@PathVariable("updateById") long updateById) { final Retailer
	 * retailer = retailerService.updateRetailerById(updateById); return new
	 * ResponseEntity(retailer, HttpStatus.OK); }
	 */

}
