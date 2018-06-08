 package com.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.bank.Exception.ManagedException;

public interface IAtmDenoService {
	
	
	
	
	void atmDenominationCreate(List<BigDecimal> list,Long atmId)throws ManagedException;
	void atmDenominationDeposit(BigDecimal amount, Long atmId) throws ManagedException;
}
