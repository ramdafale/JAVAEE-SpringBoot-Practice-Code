package model;

import java.util.Date;


/*
 *@ramdafale
 */
/*
 * @Class Statements It is use to generate a statement for a customer at what time date 
 */
public class Statement {
	

	private Date date;
	private String showmsg;
	Account account;
	/**
	 * @param date
	 * @param account
	 */
	public Statement(Date date, Account account,String showmsg) {
		super();
		this.date = date;
		this.account = account;
		this.showmsg= showmsg;
	}
	
	public Statement(Date date) {
		super();
		this.date = date;
		
	}
	
	public Statement(String showmsg) {
		super();
		this.showmsg=showmsg;
		
	}
	
	
	

}
