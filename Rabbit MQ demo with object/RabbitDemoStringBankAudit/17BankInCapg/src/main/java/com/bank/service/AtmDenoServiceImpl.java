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
import com.bank.model.ATM;
import com.bank.model.ATMDenomination;
import com.bank.model.Bank;
import com.bank.model.BankDenomination;
import com.bank.model.RefMoney;
import com.bank.repository.ATMDenoRepository;
import com.bank.repository.ATMRepository;
import com.bank.repository.DenominationRepository;

@Service
public class AtmDenoServiceImpl implements IAtmDenoService{

	
	@Autowired
	private IRefServcie refService;
	
	@Autowired
	private DenominationHelper denominationHelper;
	
	@Autowired
	private ATMDenoRepository atmdenoRepository;
	@Autowired
	private ATMRepository atmRepo;
	@Autowired
	private DenominationRepository bankDeno;
	@Autowired
	IBankService bankService;
	
	
	// this will create denominations for atm
	@Override
	public void atmDenominationCreate(List<BigDecimal> list, Long atmId) throws ManagedException {
		
		for(BigDecimal denom: list )
		{
			final ATMDenomination first = new ATMDenomination(atmId,new BigDecimal(denom.intValue()), 0);
			atmdenoRepository.save(first);

		}
	}
	
	//this method provide logic (when you add money to atm ) for denominations adjustments
	@Override
	public void atmDenominationDeposit(BigDecimal amount, Long atmId) throws ManagedException {
		// TODO Auto-generated method stub
		
          List<RefMoney> newList=refService.returnAll();
		
		List<BigDecimal> list = new ArrayList<>();
		for(RefMoney deno:newList)
		{
			list.add(deno.getDenomination());
		}
		Map<BigDecimal, Integer> denomValues = denominationHelper.getDenominatioValues(amount, list);
		
		ATMDenomination atmDeno=new ATMDenomination();
	
		long i = 0;
		Iterator<Map.Entry<BigDecimal, Integer>> it = denomValues.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<BigDecimal, Integer> pair = it.next();
		    BigDecimal myValue = pair.getKey();
		    Integer count=pair.getValue();	
			System.out.println("key value"+myValue);
			Optional<ATMDenomination> deno=atmdenoRepository.findById(myValue);
			ATMDenomination denomination=deno.get();
		System.out.println("denomination"+denomination);
		System.out.println("count value"+count);
		ATM atm=atmRepo.findById(atmId).get();
		BankDenomination bank=bankDeno.findById(myValue).get();
		int newNum=bank.getNoOfDenomination()-count;
		bank.setNoOfDenomination(newNum);
		bankDeno.save(bank);
		denomination.setATMId(atmId);
		denomination.setNoofDenomination(denomination.getNoofDenomination()+count);
		
		atmdenoRepository.save(denomination);
		}
	}
	}
	
	


