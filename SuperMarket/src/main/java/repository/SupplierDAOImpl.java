package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class SupplierDAOImpl implements SupplierDAO {

	private DataSource dataSource;

	JdbcTemplate jdbcTemplate;

	@Override
	public int addSupplier(int supplierId, String supplierName, String supplierAddress, int quantityOrder, int orderId,
			double amount) {
		String Query = "insert into supplier values(" + supplierId + "," + "'" + supplierName + "'" + "," + "'"
				+ supplierAddress + "'" + "," + quantityOrder + "," + orderId + "," + amount + ")";
		System.out.println(Query);
		return jdbcTemplate.update(Query);
	}

	@Override
	public int removeSupplier(int supplierId) {

		String query = "delete from supplier where supplierId=" + supplierId;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}


	@Override
	public int updateSupplier(int supplierId, String supplierName) {

		String query = "update supplier set supplierName="+"'" +supplierName+"'"+ "where supplierId="+supplierId;

		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
