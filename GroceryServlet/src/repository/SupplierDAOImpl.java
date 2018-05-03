package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SupplierDAOImpl implements SupplierDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param jdbcTemplate
	 */
	/*public SupplierDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}*/

	@Override
	public String addSupplier(String supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount,  String retailerId) {
		try {
			jdbcTemplate.update("INSERT INTO Supplier values('"
					+ supplierId + "','" + supplierName + "','"
					+ supplierAddress + "','" + quantityOrder + "','"
					+ orderId + "','" + amount + "','" + retailerId + "')");
		} catch (Exception e) {
			System.out.println(e);}
		return "Supplier added";
	}

	@Override
	public String removeSupplier(String supplierId) {
		try {
			String sql = "DELETE FROM Supplier where supplierId= ?";
			jdbcTemplate.update(sql,supplierId);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Supplier deleted";
	}

	@Override
	public String updateSupplier(String supplierId) {
		try {
			String sql = "UPDATE Supplier set quantityOrder=1 where supplierId=supplierId";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Supplier updated";
	}

}
