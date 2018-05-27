package com.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * author Ram 
 */
@Table(name = "customer_details")
@Entity
public class Customer extends BaseEntity implements Cloneable {

	@Id
	//@SequenceGenerator(name = "customer_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "customer_seq"*/)
	@Column(name = "id")
	private Long customerId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "pin")
	private Long pin;

	// relational mapping of bank to customer
	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * method name : getCustomerId method returnTypr : Long method description : to
	 * get customer id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * method name : setCustomerId method parameter : Long method description : it
	 * will set customerId
	 */
	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * method Name : getCustomerName method return type : String method description
	 * : it will get he customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * method name : setCustomerName method parameter : String method description :
	 * it will set customerName
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * method Name : getPin method return type : Long method description : it will
	 * get he pin
	 */
	public Long getPin() {
		return pin;
	}

	/**
	 * method name : setPin method parameter : Long method description : it will set
	 * pin
	 */
	public void setPin(final Long pin) {
		this.pin = pin;
	}

	/**
	 * method name : setBank method parameter : Bank method description : it will
	 * set Bank
	 */
	public void setBank(final Bank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", pin=" + pin + ", bank="
				+ bank + "]";
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param pin
	 */
	public Customer(Long customerId, String customerName, Long pin) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.pin = pin;
	}

	public Customer(String string, long l) {
		
	}
	@Override
	public Customer clone()throws CloneNotSupportedException{  
		return (Customer)super.clone();  
	   }
	public Customer( String customerName) {
		super();
	
		this.customerName = customerName;
		
	}
		
	
}
