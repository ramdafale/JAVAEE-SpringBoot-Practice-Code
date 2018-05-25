/**
 * 
 */
package com.springboot.bank.service;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.dto.ATMDetails;
import com.springboot.bank.dto.WrapperBankATM;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.ATM;
import com.springboot.bank.model.Account;
import com.springboot.bank.model.Bank;
import com.springboot.bank.repository.ATMDAO;
import com.springboot.bank.repository.AccountDAO;
import com.springboot.bank.repository.BankDAO;

/**
 * @author Ram
 *
 */
@Service("ATMService")
public class ATMServiceImpl implements ATMService {

	// Injectiong properties of ATMDAO
	@Autowired
	ATMDAO atmDao;

	// Injecting properties of BankDAO
	@Autowired
	BankDAO bankDao;

	// Injecting properties of AccountDAO
	@Autowired
	AccountDAO accountDao;
	
	
	@Autowired
	BankDenominationService bankDenominationService ;
	
	

	/*
	 * ATMService#createATM DESC: create instance of ATM
	 */
	@Override
	public ATM createATM(WrapperBankATM wrapperBankATM) throws BankException {
		ATM ATMData = wrapperBankATM.getAtm();
		Long bankId = wrapperBankATM.getBankId();
		Optional<Bank> bankList = bankDao.findById(bankId);
		Bank bank = bankList.get();
		if (bank == null)
			throw new BankException("Id not found");
		else {
			ATMData.setBank(bank);
			ATMData = atmDao.save(ATMData);
		}
		return ATMData;
	}

	/*
	 * ATMService#addMoneyFromBank DESC:
	*DESC this will  add  money to ATM from Bank
	*/
	@Override
	public ATM addMoneyFromBank(Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM) throws BankException {

		Optional<ATM> atmList = atmDao.findById(atmId);
		
		
		bankDenominationService.addDenominationNew(moneyToBeAddedToATM,atmId);
		
		
		ATM atmdata = null;
		ATM atm = atmList.get();
		if (atm == null)
			throw new BankException("Atm with such Id doesnt exist");
		else {
			Optional<Bank> bankList = bankDao.findById(bankId);
			Bank bank = bankList.get();
			if (bank == null)
				throw new BankException("Bank with such Id doesnt exist");
			else {
				BigDecimal bankMoney = bank.getAmount();
				BigDecimal finalAmount = bankMoney.subtract(moneyToBeAddedToATM);
				if (finalAmount.compareTo(BigDecimal.ZERO) == 1) {
					BigDecimal atmMoney = atm.getMoney().add(moneyToBeAddedToATM);
					atm.setMoney(atmMoney);
					bank.setAmount(finalAmount);
					atmdata = atmDao.save(atm);
					bankDao.save(bank);
				} else
					throw new BankException("Bank doesnt have enough money");
			}
			return atmdata;
		}
	}

	@Override
	public ATM withdrawMoney(ATMDetails atmDetails) throws BankException {
		Long atmId = atmDetails.getAccountId();
		Long bankId = atmDetails.getBankId();
		Long accountId = atmDetails.getAccountId();
		BigDecimal amountToBeWithdrawn = atmDetails.getAmount();
		Account account = null;
		Bank bank = null;
		ATM atm = null;
		if (accountId == 0 || bankId == 0 || atmId == 0) {
			throw new BankException("Id cannot be zero");
		} else {
			Optional<Account> accountList = accountDao.findById(accountId);
			account = accountList.get();
			BigDecimal newAccountBalance;
			if (account == null) {
				throw new BankException("No such account exists");
			} else {
				newAccountBalance = amountToBeWithdrawn.subtract(account.getAmount());
				if (newAccountBalance.compareTo(BigDecimal.ZERO) == 1) {
					account.setAmount(newAccountBalance);
					accountDao.save(account);
				} else {
					throw new BankException("Account Balance cannot be negative");
				}
			}
			Optional<Bank> bankList = bankDao.findById(bankId);
			bank = bankList.get();
			if (bank == null) {
				throw new BankException("No such id of Bank exists");
			} else {
				BigDecimal newBankBalance = amountToBeWithdrawn.subtract(bank.getAmount());
				if (newBankBalance.compareTo(BigDecimal.ZERO) == 1) {
					bank.setAmount(newBankBalance);
					bankDao.save(bank);
				} else {
					throw new BankException("Bank Balance cannot be negative");
				}
			}
			Optional<ATM> atmList = atmDao.findById(atmId);
			atm = atmList.get();
			if (atm == null) {
				throw new BankException("No such id of ATM exists");
			} else {
				BigDecimal newBankBalance = amountToBeWithdrawn.subtract(bank.getAmount());
				if (newBankBalance.compareTo(BigDecimal.ZERO) == 1) {
					atm.setMoney(newAccountBalance);
					atmDao.save(atm);
				} else {
					throw new BankException("ATM Balance cannot be negative");
				}
			}
		}
		return atm;
	}
}
