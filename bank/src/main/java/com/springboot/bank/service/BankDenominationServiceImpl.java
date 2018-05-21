/**
 * 
 */
package com.springboot.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.dto.WrapperBankDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.BankDenomination;
import com.springboot.bank.model.BankDenomination;
import com.springboot.bank.repository.BankDAO;
import com.springboot.bank.repository.BankDenominationDAO;

/**
 * @author Sumit
 *
 */
@Service("bankDenominationService")
public class BankDenominationServiceImpl implements BankDenominationService {

	@Autowired
	private BankDenominationDAO bankDenominationDAO;

	@Autowired
	BankDAO bankDao;

	/*
	 * @Override public BankDenomination addDenomination(WrapperBankDenomination
	 * wrapperBankDenomination) throws BankException { Long bankId =
	 * wrapperBankDenomination.getBankId(); Integer amount =
	 * wrapperBankDenomination.getAmount(); BankDenomination bankDenomination =
	 * wrapperBankDenomination.getBankDenomination(); Bank bank = null; Integer
	 * noOf2000s = null; Integer noOf500s = null; Integer noOf100s = null; Integer
	 * temperoryCount = 0; if (amount / 2000 != 0) { noOf2000s = amount / 2000;
	 * amount = amount % 2000; System.out.println(noOf2000s); temperoryCount =
	 * temperoryCount + noOf2000s; bankDenomination.setNoOf2000s(noOf2000s); } if
	 * (amount != 0 & amount / 500 != 0) { noOf500s = amount / 500; amount = amount
	 * % 500; temperoryCount = temperoryCount + noOf500s;
	 * bankDenomination.setNoOf500s(noOf500s); }
	 * 
	 * if (amount != 0 & amount / 100 != 0) { noOf100s = amount / 100; amount =
	 * amount % 100; temperoryCount = temperoryCount + noOf100s;
	 * bankDenomination.setNoOf100s(noOf100s); }
	 * 
	 * Optional<Bank> bankList = bankDao.findById(bankId); if (bankList.isPresent())
	 * { bank = bankList.get(); bankDenomination.setBank(bank);
	 * bankDenomination.setNoOfDenomination(temperoryCount);
	 * bankDenominationDAO.save(bankDenomination); } else { throw new
	 * BankException("bank is not present"); } return bankDenomination; }
	 */

	@Override
	public void addDenominationNew(Integer amount) throws BankException {

		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2000);
		list1.add(500);
		list1.add(100);
		list1.add(200);
		Random rand = new Random();
		Integer remainder = amount;
		System.out.println("list >>>>" + list1);
		for (int i = 0; i <= list1.size(); i++) {
			Integer randomIndex = rand.nextInt(list1.size());
			// System.out.println("randomIndex >>" + randomIndex);
			Integer randomElement = list1.get(randomIndex);
			// System.out.println("randomElement >>" + randomElement);
			if (randomElement.compareTo(remainder) == 0 || randomElement.compareTo(remainder) == -1) {
				BankDenomination bankDenominationNew = new BankDenomination();
				bankDenominationNew.setNoOfDenomination(remainder / randomElement);
				bankDenominationNew.setDenomination(randomElement);
				remainder = remainder % randomElement;
				System.out.println(bankDenominationNew);
				if (remainder == 0) {
					break;
				}
			}
			list1.remove(randomIndex);
		}

	}
}