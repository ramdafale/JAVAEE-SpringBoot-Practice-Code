package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.Exception.ManagedException;
import com.bank.model.Bank;

public interface IBankService {
	/*
	 * description : this method will return a bank which is created
	 */
	Bank createBank(final Bank bank) throws ManagedException;

	/* description : this method will return a list of all banks
	 */
	List<Bank> getBankDetails() throws ManagedException;

	/*
	 * description : this method will return a bank of given id
	 */
	Optional<Bank> getBankDetailsByID(final long Id) throws ManagedException;

}
