package repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {

	private DataSource dataSource;
@Autowired
JdbcTemplate jdbcTemplate;


	
	
	
	
	@Override
	public int addSupplier(final int supplierId, final String supplierName, final String supplierAddress, final int quantityOrder, final int orderId,
			final double amount) {
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
/*
	*//**
	 * @return the jdbcTemplate
	 *//*
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	*//**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 *//*
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

}
