package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.dto.CreateDenominationRequest;
import com.bank.service.IAtmDenoService;
import com.bank.service.IBankDenominationService;
import com.bank.service.IRefServcie;

@RestController
public class DenominationController {

	@Autowired
	private IRefServcie refService;
	
	@Autowired
	private IBankDenominationService bankDenominations;
	@Autowired
	private IAtmDenoService atmDenominationService;
	
	
	
	// this will add denominations to bank and atm
	@PostMapping("/addDenominations")
	public void addDenomination(@RequestBody CreateDenominationRequest createDenomination)
	{
		
		try {
			refService.createDenomination(createDenomination.getList());
		} catch (ManagedException e1) {
			
			e1.printStackTrace();
		}
		try {
			bankDenominations.bankDenominationCreate(createDenomination.getList(),createDenomination.getBankId());
		} catch (ManagedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			atmDenominationService.atmDenominationCreate(createDenomination.getList(), createDenomination.getAtmId());
		} catch (ManagedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
