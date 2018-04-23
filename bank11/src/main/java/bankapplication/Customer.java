/**
 * 
 */
package bankapplication;

/**
 * @author trainee
 *
 */
class Customer {
	String fname = null;
	String lname = null;
	String address = null;
	long number;
	String email;

	/**
	 * @param fname
	 * @param lname
	 * @param address
	 * @param number
	 * @param email
	 */
	public Customer(String fname, String lname, String address, long number, String email) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.number = number;
		this.email = email;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
