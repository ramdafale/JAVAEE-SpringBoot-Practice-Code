
package com.training.springbootjpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  RamDAfale
 *
 */
@Entity
@Table(name = "retailer")
public class Retailer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long retailerId;
	private String retailerName;
	private String retailerAddress;

	/**
	 * @param retailerId
	 * @param retailerName
	 * @param retailerAddress
	 */
	public Retailer(Long retailerId, String retailerName, String retailerAddress) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.retailerAddress = retailerAddress;
	}

	/**
	 * 
	 */
	public Retailer() {
		super();
	}

	/**
	 * @return the retailerId
	 */
	public Long getRetailerId() {
		return retailerId;
	}

	/**
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(Long retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * @return the retailerName
	 */
	public String getRetailerName() {
		return retailerName;
	}

	/**
	 * @param retailerName
	 *            the retailerName to set
	 */
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	/**
	 * @return the retailerAddress
	 */
	public String getRetailerAddress() {
		return retailerAddress;
	}

	/**
	 * @param retailerAddress
	 *            the retailerAddres to set
	 */
	public void setRetailerAddress(String retailerAddress) {
		this.retailerAddress = retailerAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retailer [retailerId=" + retailerId + ", retailerName=" + retailerName + ", retailerAddress="
				+ retailerAddress + ", getRetailerId()=" + getRetailerId() + ", getRetailerName()=" + getRetailerName()
				+ ", getRetailerAddress()=" + getRetailerAddress() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
