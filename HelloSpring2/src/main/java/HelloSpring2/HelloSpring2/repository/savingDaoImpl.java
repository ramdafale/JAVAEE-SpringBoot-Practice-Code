/**
 * 
 */
package HelloSpring2.HelloSpring2.repository;

import java.util.HashMap;
import java.util.Map;

import HelloSpring2.HelloSpring2.Customer;
import HelloSpring2.HelloSpring2.savingAccount;

/**
 * @author trainee
 *
 */
public class savingDaoImpl implements savingAccountDao {


	Map<String, savingAccount> map = new HashMap<String, savingAccount>();
	
	public void addSavingAccount(savingAccount sa) {
		// TODO Auto-generated method stub

		
		map.put(sa.getAccountNumber(),sa);
	}

	
}
