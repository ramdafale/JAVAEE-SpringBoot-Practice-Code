package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.model.RefMoney;
import com.bank.repository.RefMoneyRepository;
@Service
public class RefServcieImpl implements IRefServcie {
	
@Autowired
private RefMoneyRepository refRepo;
	@Override
	public void createDenomination(List<BigDecimal> list) throws ManagedException{
		for(BigDecimal denomination: list )
		{
		if(!refRepo.findById(denomination).isPresent())
		{
			RefMoney refMoney=new RefMoney();
			refMoney.setDenomination(denomination);
			refRepo.save(refMoney);
			//return true;
		}
		else
		{
			throw new ManagedException("Denomination already exist!!!!");
		}
	}
	}

	@Override
	public List<RefMoney> returnAll() throws ManagedException {
		
		List<RefMoney> list=refRepo.findAll();
		if(list.isEmpty())
		{
			throw new ManagedException("no denomination found");
		}
		else
		{
			return list;
		}
	}

}
