/**
 * 
 */
package com.springboot.bank.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author trainee
 *
 */
@MappedSuperclass
public class BaseEntity {

	@Column(name="UserId")
	String userID;
}
