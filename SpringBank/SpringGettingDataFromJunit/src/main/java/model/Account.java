package model;

import java.util.List;
/*
 * @RamDafale
 * 
 */

/*
 * @Class this class is use to create a account of customer
 * 
 */
/**
 * @author trainee
 *
 */
public class Account {

	private String accountNo;
	private double interestRate;
	private double balance;
	private int interestPeriod;
	private List<Statement> statementList;
	private String typeOfAccount;

	public Account(final String accountNo, final double interestRate, final double balance, final int interestPeriod,
			final List<Statement> statementList, final String typeOfAccount) {
		this.accountNo = accountNo;
		this.interestRate = interestRate;
		this.balance = balance;
		this.interestPeriod = interestPeriod;
		this.statementList = statementList;
		this.typeOfAccount = typeOfAccount;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 *            the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * @param interestRate
	 *            the interestRate to set
	 */
	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the money
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setBalance(double money) {
		this.balance = money;
	}

	/**
	 * @return the interestPeriod
	 */
	public int getInterestPeriod() {
		return interestPeriod;
	}

	/**
	 * @param interestPeriod
	 *            the interestPeriod to set
	 */
	public void setInterestPeriod(int interestPeriod) {
		this.interestPeriod = interestPeriod;
	}

	/**
	 * @return the statementList
	 */
	public List<Statement> getStatementList() {
		return statementList;
	}

	/**
	 * @param statementList
	 *            the statementList to set
	 */
	public void setStatementList(List<Statement> statementList) {
		this.statementList = statementList;
	}

	/**
	 * @return the typeOfAccount
	 */
	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	/**
	 * @param typeOfAccount
	 *            the typeOfAccount to set
	 */
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

}
