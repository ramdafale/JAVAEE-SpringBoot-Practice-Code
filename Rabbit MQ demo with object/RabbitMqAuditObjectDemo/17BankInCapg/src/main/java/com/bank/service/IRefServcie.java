package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.bank.Exception.ManagedException;
import com.bank.model.RefMoney;

/**
 * @author ram
 *
 */
public interface IRefServcie {
	
	/**
	 * 
	 * @param denomination
	 * @return
	 * @throws BankException
	 */
	
	/**
	 * 
	 * @return
	 * @throws ManagedException
	 */
	List<RefMoney> returnAll() throws ManagedException;
	void createDenomination(List<BigDecimal> list) throws ManagedException;

}
