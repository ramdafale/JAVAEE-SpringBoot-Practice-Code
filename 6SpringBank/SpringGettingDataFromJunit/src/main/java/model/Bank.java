package model;

import java.util.List;

public class Bank {
	
	private String bankName;
    List<Customer> custom;
	
	
	
	public Bank(final String bankName, final List<Customer> custom) {
		this.bankName = bankName;
		this.custom = custom;
	}

	public List<Customer> getCustomer() {
		return custom;
	}

	public void setCustomer(final List<Customer> custom) {
		this.custom = custom;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}
	
	
	
	
	

}
