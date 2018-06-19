package com.bank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.model.Bank;
import com.bank.model.BankDenomination;
import com.bank.model.RefMoney;
import com.bank.repository.DenominationRepository;

@Service
public class BankDenominationServiceImpl implements IBankDenominationService {
	@Autowired
	private DenominationRepository denominationRepo;
	@Autowired
	private IBankService bankServcie;
	@Autowired
	private DenominationHelper denominationHelper;
	@Autowired
	private IRefServcie refService;

	
	//create denomination for bank
	@Override
	public void bankDenominationCreate(List<BigDecimal> list, Long bankId) throws ManagedException {

		// to get values from database
		for (BigDecimal denom : list) {
			final BankDenomination first = new BankDenomination(new BigDecimal(denom.intValue()), 0, bankId);
			denominationRepo.save(first);

		}

	}

	
	//this will provide logic:  when you withdraws money denominations will get adjusted accordingly
	
	@Override
	public void bankDenominationWithdraw(BigDecimal amount, Long bankId) throws ManagedException {
		List<RefMoney> newList = refService.returnAll();
		List<BigDecimal> list = new ArrayList<>();
		for (RefMoney deno : newList) {
			list.add(deno.getDenomination());
		}
		Map<BigDecimal, Integer> denomValues = denominationHelper.getcountDenomination(amount, list);
		long i = 0;
		Iterator<Map.Entry<BigDecimal, Integer>> it = denomValues.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BigDecimal, Integer> pair = it.next();
			BigDecimal myValue = pair.getKey();
			Integer count = pair.getValue();
			System.out.println("key value" + myValue);
			Optional<BankDenomination> deno = denominationRepo.findById(myValue);
			BankDenomination denomination = deno.get();
			System.out.println("denomination" + denomination);
			System.out.println("count value" + count);
			Bank bank = bankServcie.getBankDetailsByID(bankId).get();
			denomination.setBankId(bankId);
			denomination.setNoOfDenomination(denomination.getNoOfDenomination() - count);
			denominationRepo.save(denomination);

		}

	}

	@Override
	public void bankDenominationDeposit(BigDecimal amount, Long bankId) throws ManagedException {

		List<RefMoney> newList = refService.returnAll();

		List<BigDecimal> list = new ArrayList<>();
		for (RefMoney deno : newList) {
			list.add(deno.getDenomination());
		}
		Map<BigDecimal, Integer> denomValues = denominationHelper.getDenominatioValues(amount, list);

		BankDenomination bankDeno = new BankDenomination();

		long i = 0;
		Iterator<Map.Entry<BigDecimal, Integer>> it = denomValues.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BigDecimal, Integer> pair = it.next();
			BigDecimal myValue = pair.getKey();
			Integer count = pair.getValue();

			Optional<BankDenomination> deno = denominationRepo.findById(myValue);
			BankDenomination denomination = deno.get();
			Bank bank = bankServcie.getBankDetailsByID(bankId).get();
			denomination.setBankId(bankId);
			denomination.setNoOfDenomination(denomination.getNoOfDenomination() + count);
			denominationRepo.save(denomination);
		}

	}

}
