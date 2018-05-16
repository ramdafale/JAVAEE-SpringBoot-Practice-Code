/**
 * 
 */
package com.example.service;

import com.example.model.Bank;

/**
 * @author trainee
 *
 */
interface IBankService {

	
	int addBank(Bank bank);
	
	Object getBankDetails(Bank bank);
	
}
