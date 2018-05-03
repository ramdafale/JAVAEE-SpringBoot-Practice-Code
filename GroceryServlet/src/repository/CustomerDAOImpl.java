package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param temperoryJdbcTemplate
	 */
/*	public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}*/

	@Override
	public String addCustomer(final String customerId, final String customerName,
			final String customerAddress, final String paymentMode, final String retailerId) {
		try {
			jdbcTemplate.update("INSERT INTO Customer values('"
					+ customerId + "','" + customerName + "','"
					+ customerAddress + "','" + paymentMode + "','"
					+ retailerId + "')");
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Customer added";
	}

	@Override
	public String removeCustomer(final String customerId) {
		try {
			String sql = "DELETE FROM Customer where customerId = ?";
			jdbcTemplate.update(sql, customerId);

		} catch (Exception e) {
			System.out.println(e);
		}
		return "Customer deleted";
	}

	@Override
	public String updateCustomer(String customerId) {
		try {
			String sql = "UPDATE Customer set paymentMode='CARD' where customerId = ?";
			jdbcTemplate.update(sql, customerId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Customer updated";
	}
}