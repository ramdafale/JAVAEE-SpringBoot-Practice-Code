
package model;

/**
 * @author Ram
 *
 */
public class Retailer {

	private String retailerName;
	private String retailerAddres;
	/**
	 * @param retailerName
	 * @param retailerAddres
	 */
	public Retailer(String retailerName, String retailerAddres) {
		this.retailerName = retailerName;
		this.retailerAddres = retailerAddres;
	}

	public Retailer() {
		// TODO Auto-generated constructor stub
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
	 * @return the retailerAddres
	 */
	public String getRetailerAddres() {
		return retailerAddres;
	}

	/**
	 * @param retailerAddres
	 *            the retailerAddres to set
	 */
	public void setRetailerAddres(String retailerAddres) {
		this.retailerAddres = retailerAddres;
	}


}
