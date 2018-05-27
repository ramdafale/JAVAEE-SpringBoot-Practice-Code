/**
 * 
 */
package com.springboot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */

public interface BankDenominationDAO extends JpaRepository<Denomination, Long> {

}
