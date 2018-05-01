package repository;

import org.springframework.jdbc.core.JdbcTemplate;

import model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addCustomer(Customer e) {

		String query = "insert into customer values('" + e.getCustomerId() + "','" + e.getCustomerName() + "','"
				+ e.getCustomerAddress() + "','" + e.getPaymentMode() + "')";
		System.out.println(query);
		return jdbcTemplate.update(query);

	}

	public int updateCustomer(int customerID, String name) {

		String query = "update customer set customerName=" + "'" + name + "'" + " where customerId=" + customerID;
		// String query = "update customer set customerName="+name+" where customerId="+
		// customerID;

		System.out.println(query);
		return jdbcTemplate.update(query);

	}

	public int removeCustomer(int id) {
		String query = "delete from customer where customerId="+id;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}
}
