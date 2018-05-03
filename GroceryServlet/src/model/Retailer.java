
package model;

/**
 * @author Sumit
 *
 */
public class Retailer {

	private String retailerId;
	private String retailerName;
	private String retailerAddress;

	/**
	 * @param retailerId
	 * @param retailerName
	 * @param retailerAddress
	 */
	public Retailer(String retailerId, String retailerName, String retailerAddress) {
		super();
		this.retailerId = retailerId;
		this.retailerName = retailerName;
		this.retailerAddress = retailerAddress;
	}

	/**
	 * @return the retailerId
	 */
	public String getRetailerId() {
		return retailerId;
	}

	/**
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(String retailerId) {
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
}
