package com.spring.security.web;




import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.security.web.entity.Role;

import lombok.Data;

/**
 * @author ram
 *
 */
@Entity
@Table(name = "authority")
@Data
public class Authority {

	private String department;
	@ManyToOne(targetEntity = Role.class)
	private Role role;

	/**
	 * 
	 */
	public Authority() {
		super();
	}

	/**
	 * @param department
	 * @param role
	 */
	public Authority(String department) {
		super();
		this.department = department;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
