package model;

/*
 * ramdafale
 * 
 */
public class Contact {
	/*
	 * Creating Contact clsass for initializing contact details
	 * 
	 */

	private Integer phoneNumber;
	private int mobileNumber;
	private String email;
	Address addr;

	public Contact(final Integer phoneNumber, final int mobileNumber, final String email, final Address addr) {
		super();
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.addr = addr;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(final int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(final Address addr) {
		this.addr = addr;
	}

}
