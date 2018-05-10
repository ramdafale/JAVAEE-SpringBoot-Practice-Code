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
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long supplierId;
	private String supplierName;
	private String supplierAddress;
	private int quantityOrder;
	private int orderId;
	private Double amount;


	/**
	 * 
	 */
	public Supplier() {
		super();
	}

	/**
	 * @param supplierId
	 * @param supplierName
	 * @param supplierAddress
	 * @param quantityOrder
	 * @param orderId
	 * @param amount
	 * @param retailerId
	 */
	public Supplier(Long supplierId, String supplierName, String supplierAddress, int quantityOrder, int orderId,
			Double amount, String retailerId) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.quantityOrder = quantityOrder;
		this.orderId = orderId;
		this.amount = amount;
		
	}

	/**
	 * @return the supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the supplierAddress
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}

	/**
	 * @param supplierAddress
	 *            the supplierAddress to set
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	/**
	 * @return the quantityOrder
	 */
	public int getQuantityOrder() {
		return quantityOrder;
	}

	/**
	 * @param quantityOrder
	 *            the quantityOrder to set
	 */
	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierAddress="
				+ supplierAddress + ", quantityOrder=" + quantityOrder + ", orderId=" + orderId + ", amount=" + amount
				+ "]";
	}
}
