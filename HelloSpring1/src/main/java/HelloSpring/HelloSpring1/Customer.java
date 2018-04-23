package HelloSpring.HelloSpring1;

import java.util.List;

/**
 * @author Ram Dafale
 *
 */
public class Customer implements  ICustomerDAO {
	
	
	final	private String firstanme;
	 final private String lastname;
	


/**
	 * @param firstanme
	 * @param lastname
	 */
	
	 
	 
	 
	 
	public Customer(String firstanme, String lastname) {
	//	super();
		this.firstanme = firstanme;
		this.lastname = lastname;
	}
/**
 * @return the firstanme
 */
public String getFirstanme() {
	return firstanme;
}
/**
 * @return the lastname
 */
public String getLastname() {
	return lastname;
}


public List<String> getAll() {
	// TODO Auto-generated method stub
	return null;
}

 
 
 
 
}
