package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Customer;
@Repository()
@Qualifier("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {
	
	/*
	 * 
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	

	public int updateCustomer(final int customerID, final String name) {

		String query = "update customer set customerName=" + "'" + name + "'" + " where customerId=" + customerID;
		// String query = "update customer set customerName="+name+" where customerId="+
		// customerID;

		System.out.println(query);
		return jdbcTemplate.update(query);

	}

	public int removeCustomer(final int identy) {
		final String query = "delete from customer where customerId="+identy;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	@Override
	public int addCustomer(Customer c) {
		String query = "insert into customer values('" + c.getCustomerId() + "','" + c.getCustomerName() + "','"
				+ c.getCustomerAddress() + "','" + c.getPaymentMode() + "')";
		System.out.println(query);
		return jdbcTemplate.update(query);
	}
}
