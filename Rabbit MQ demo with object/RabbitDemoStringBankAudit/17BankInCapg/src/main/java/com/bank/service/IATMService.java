package com.bank.service;

import com.bank.Exception.ManagedException;
import com.bank.dto.AddMoneyReq;
import com.bank.dto.WithrawMoneyReq;
import com.bank.model.ATM;

/**
 * @author ram
 */
public interface IATMService {
	/**
	 * method name : createATM return type : ATM object parameter :object of ATM
	 * description : this method will return a atm which is created
	 */
	ATM createATM(final ATM atm) throws ManagedException;

	/**
	 * method name : addMoneyFromBank return type : String parameter :object of
	 * ATMMoneyReq description : this method will return a string if money is added
	 * to atm
	 */
	String addMoneyFromBank(final AddMoneyReq atm) throws ManagedException;

	/**
	 * method name : withrawMoney return type : String parameter :object of
	 * WithdrawMoneyReq description : this method will return a string if
	 * successfully money withraw
	 */
	Long withrawMoney(final WithrawMoneyReq withdraw) throws ManagedException;

}
