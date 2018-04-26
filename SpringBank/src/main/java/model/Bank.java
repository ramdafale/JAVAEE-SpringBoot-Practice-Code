/**
 * 
 */
package model;

import java.util.logging.Logger;

import bankapplication.Apps;

/**
 * @author trainee
 *
 */
public class Bank {
	static String name = "-------Indian Bank------";

	public void bankName() {

	  final  Logger LOGGER = Logger.getLogger(Apps.class.getName());
	  LOGGER.info(name);

	}

}
