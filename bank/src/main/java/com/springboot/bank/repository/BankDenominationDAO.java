/**
 * 
 */
package com.springboot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.bank.model.BankDenomination;

/**
 * @author Sumit
 *
 */

public interface BankDenominationDAO extends JpaRepository<BankDenomination, Long> {

}
