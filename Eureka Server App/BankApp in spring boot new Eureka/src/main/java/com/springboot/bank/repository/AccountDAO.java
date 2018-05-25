/**
 * 
 */
package com.springboot.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.bank.model.Account;

/**
 * @author Ram
 *
 */
public interface AccountDAO extends JpaRepository<Account, Long>{

}
