package com.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bank.Exception.ManagedException;

public interface IBankDenominationService {
	
	
	void bankDenominationWithdraw(BigDecimal amount,Long bankId) throws ManagedException;
	void bankDenominationDeposit(BigDecimal amount, Long bankId) throws ManagedException ;
	void bankDenominationCreate(List<BigDecimal> list,Long bankId)throws ManagedException;

}
