package model;

/**
 * @author Ram
 * 
 */




/**
 * @class  this class represent a customer entity 
 * 
 */
public class Customer {

	/**
	 * @field this is use to provide particular id which will represent a whole
	 *        record in table
	 */
	private int customerId;

	/**
	 * @field this is use to provide particular id which will represent a whole
	 *        record in table
	 */
	private String customerName;
	/**
	 * @field this is use to represent a name of the customer
	 * 
	 */
	private String customerAddress;
	/**
	 * @field this is use to represent a payment of the customer
	 * 
	 */
	private String paymentMode;

	/**
	 * @param customerId
	 * @param customerName
	 * @param customerAddress
	 * @param paymentMode
	 */
	public Customer(final int customerId, final String customerName, final String customerAddress,
			final String paymentMode) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.paymentMode = paymentMode;
	}

	/**
	 * 
	 * @param customerName
	 * 
	 */
	public Customer(final String customerName) {

		this.customerName = customerName;

	}

	/**
	 * 
	 * @constructor this is default constructor
	 * 
	 */
	public Customer() {

	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(final int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress
	 *            the customerAddress to set
	 */
	public void setCustomerAddress(final String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 */
	public void setPaymentMode(final String paymentMode) {
		this.paymentMode = paymentMode;
	}

}
