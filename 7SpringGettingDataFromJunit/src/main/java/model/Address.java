package model;

/*
 * @ramdafale
 * 
 */
public class Address {
/*
* Creating Address class for initializing address
* 
*/

	private String street;
	private String houseNumber;
	private String zipCode;
	private String city;
	
	public Address(String street, String houseNumber, String zipCode, String city) {
		super();
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getZopCode() {
		return zipCode;
	}
	public void setZopCode(String zopCode) {
		this.zipCode = zopCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	

}
