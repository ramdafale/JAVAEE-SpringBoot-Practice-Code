package com.example.bank.service;

/**
 * 
 */


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.ATM;
import com.example.bank.model.Bank;
import com.example.bank.repository.ATMRepository;
import com.example.bank.repository.BankRepository;
import com.example.bank.request.DetailsBankATM;


/**
 * @author ram
 *
 */
@Service("ATMService")
public class ATMServiceImpl implements IATMService {

	@Autowired
	ATMRepository atmDao;

	@Autowired
	BankRepository bankDao;

	
	@Override
	public ATM createATM(DetailsBankATM wrapperBankATM) throws ManagedException {
		ATM ATMData = wrapperBankATM.getAtm();
		Long bankId = wrapperBankATM.getBankId();
		Optional<Bank> bankList = bankDao.findById(bankId);
		Bank bank = bankList.get();
		if (bank == null)
			throw new ManagedException("Id not found");
		else {
			ATMData.setBank(bank);
			ATM ATMDataDummy = atmDao.save(ATMData);
		}
		return ATMData;
	}

	@Override
	public ATM addMoneyFromBank(Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM) throws ManagedException {

		Optional<ATM> atmList = atmDao.findById(atmId);
		ATM atmdata = null;
		ATM atm = atmList.get();
		if (atm == null)
			throw new ManagedException("Atm with such Id doesnt exist");
		else {
			Optional<Bank> bankList = bankDao.findById(bankId);
			Bank bank = bankList.get();
			if (bank == null)
				throw new ManagedException("Bank with such Id doesnt exist");
			else {
				BigDecimal bankMoney = bank.getAmount();
				BigDecimal finalAmount = bankMoney.subtract(moneyToBeAddedToATM);
				if (finalAmount.compareTo(BigDecimal.ZERO) > 1000) {
					atm.setMoney(moneyToBeAddedToATM);
					bank.setAmount(finalAmount);
					atmdata = atmDao.save(atm);
					bankDao.save(bank);
				} else
					throw new ManagedException("Bank doesnt have enough money");
			}
			return atmdata;
		}
	}

	public ATM withdrawMoney(Long atmId, Long bankId, BigDecimal moneyToBeDeductedFromATM) throws ManagedException {

		Optional<ATM> atmList = atmDao.findById(atmId);
		ATM atm = atmList.get();
		if (atm == null)
			throw new ManagedException("Atm with such Id doesnt exist");
		else {
			Bank bank = atm.getBank();
			BigDecimal amount = bank.getAmount();
			BigDecimal dummy = amount.subtract(moneyToBeDeductedFromATM);
			if (dummy.compareTo(BigDecimal.ZERO) > 1000) {
				atm.setMoney(moneyToBeDeductedFromATM);
				bank.setAmount(dummy);
			} else
				throw new ManagedException("Bank doesnt have enough money");
		}

		return null;
	}
}
