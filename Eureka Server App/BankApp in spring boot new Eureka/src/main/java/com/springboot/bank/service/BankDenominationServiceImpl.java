/**
 * 
 */
package com.springboot.bank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.dto.WrapperDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Account;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.Denomination;
import com.springboot.bank.model.Denomination;
import com.springboot.bank.repository.BankDAO;
import com.springboot.bank.repository.BankDenominationDAO;

/**
 * @author Ram
 *
 */
@Service("bankDenominationService")
public class BankDenominationServiceImpl implements BankDenominationService {

	
	
	private static final Logger LOGGER = Logger.getLogger( Account.class.getName() );
	
	
	
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
	public void addDenominationNew(BigDecimal amount,Long Id) throws BankException {
	final	List<BigDecimal> list1 = new ArrayList<BigDecimal>();
		
	
	
	
		
		BigDecimal num1 = new BigDecimal(2000);
		BigDecimal num2 = new BigDecimal(500);
		BigDecimal num3 = new BigDecimal(100);
		BigDecimal num4 = new BigDecimal(200);
		
		
		
		list1.add(num1);
		list1.add(num2);
		list1.add(num3);
		list1.add(num4);
		
		
		
		
		
		
		
		
		final	Random rand = new Random();
		BigDecimal remainder = amount;
		//System.out.println("list >>>>" + list1);
		LOGGER.info("list >>>>" + list1);
		for (int i = 0; i <= list1.size(); i++) {
			final Integer randomIndex = rand.nextInt(list1.size());
			// System.out.println("randomIndex >>" + randomIndex);
			final BigDecimal randomElement = list1.get(randomIndex);
			// System.out.println("randomElement >>" + randomElement);
			if (randomElement.compareTo(remainder) == 0 || randomElement.compareTo(remainder) == -1) {
				final	Denomination bankDenominationNew = new Denomination();
				BigDecimal res = remainder.divide(randomElement);
				
				bankDenominationNew.setNoOfDenomination(res);
				bankDenominationNew.setDenomination(randomElement);
				BigDecimal res1 = remainder.remainder(randomElement);
				
				//remainder = remainder % randomElement;
				//System.out.println(bankDenominationNew);
				LOGGER.info(">>>> Denomination"+bankDenominationNew);
				if (res1.compareTo(BigDecimal.ZERO) == 0) {
					break;
				}
			}
			list1.remove(randomIndex);
		}
		
		
	}
}