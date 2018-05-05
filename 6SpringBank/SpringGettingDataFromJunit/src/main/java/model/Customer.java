package model;

import java.util.List;




public class Customer {
	
	private String firstname;
	private String surname;
	private Contact contobj;
	private List<Account> accunlist;
	private Integer customerId;
	
	
	public Customer(final String firstname,final String surname,final Contact contobj,final List<Account> accunlist,final Integer customerId) {
		this.firstname = firstname;
		this.surname = surname;
		this.contobj = contobj;
		this.accunlist = accunlist;
		this.customerId = customerId;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(final String surname) {
		this.surname = surname;
	}
	public Contact getContobj() {
		return contobj;
	}
	public void setContobj(final Contact contobj) {
		this.contobj = contobj;
	}
	public List<Account> getAccunlist() {
		return accunlist;
	}
	public void setAccunlist(final List<Account> accunlist) {
		this.accunlist = accunlist;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(final Integer customerId) {
		this.customerId = customerId;
	}


}
