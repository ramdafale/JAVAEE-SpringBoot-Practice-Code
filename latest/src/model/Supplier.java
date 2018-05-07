package model;

import java.util.List;

/**
 * @author Sumit
 *
 */
public class Supplier {

	private int supplierId;
	private String supplierName;
	private String supplierAddress;
	private int quantityOrder;
	private int orderId;
	private double amount;
	List<Goods> goodsList;

	/**
	 * @param supplierId
	 * @param supplierName
	 * @param supplierAddress
	 * @param quantityOrder
	 * @param orderId
	 * @param amount
	 * @param goodsList
	 */
	public Supplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount, List<Goods> goodsList) {
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.quantityOrder = quantityOrder;
		this.orderId = orderId;
		this.amount = amount;
		this.goodsList = goodsList;
	}

	public Supplier() {
		// TODO Auto-generated constructor stub
	}

	public Supplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount) {
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
	public int getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(int supplierId) {
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
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the goodsList
	 */
	public List<Goods> getGoodsList() {
		return goodsList;
	}

	/**
	 * @param goodsList
	 *            the goodsList to set
	 */
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierAddress="
				+ supplierAddress + ", quantityOrder=" + quantityOrder + ", orderId=" + orderId + ", amount=" + amount
				+ ", goodsList=" + goodsList + "]";
	}
	
	
	

}
