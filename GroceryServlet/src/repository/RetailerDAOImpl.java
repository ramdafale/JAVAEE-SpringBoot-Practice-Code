package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Goods;
import model.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class RetailerDAOImpl implements RetailerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 */
/*	public RetailerDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}*/

	public String addRetailerAndGetId(String retailerId, String retailerName, String retailerAddress) {

		try {
			jdbcTemplate.update("INSERT INTO retailer values('" + retailerId + "','" + retailerName + "','"
					+ retailerAddress + "')");
		} catch (Exception e) {
			System.out.println(e);
		}
		return retailerId;
	}

	public List<Customer> viewCustomer(String retailerId) {

		// String sql = "SELCET * FROM customer where retailerId = ?";
		String sql = "SELECT * FROM customer ";

		List customerList = jdbcTemplate.query(sql, new ResultSetExtractor<List>() {
			List customerList = new ArrayList<Customer>();

			public List extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					Customer customer = new Customer(rs.getString("customerId"), rs.getString("customerName"),
							rs.getString("customerAddress"), rs.getString("paymentMode"), rs.getString("retailerId"));
					customerList.add(customer);
				}
				return customerList;
			}
		});
		return customerList;
	}

	public List<Supplier> viewSupplier(String retailerId) {

		// String sql = "SELCET * FROM customer where retailerId = ?";
		String sql = "SELECT * FROM supplier ";
		List supplierList = jdbcTemplate.query(sql, new ResultSetExtractor<List>() {

			public List extractData(ResultSet rs) throws SQLException, DataAccessException {
				List supplierList = new ArrayList<Customer>();
				while (rs.next()) {
					Supplier supplier = new Supplier(rs.getString("supplierId"), rs.getString("supplierName"),
							rs.getString("supplierAddress"), rs.getInt("quantityOrder"), rs.getInt("orderId"),
							rs.getDouble("amount"), rs.getString("retailerId"));
					supplierList.add(supplier);
				}
				return supplierList;
			}
		});
		return supplierList;
	}

	public List<Goods> viewGoods(String retailerId) {
		String sql = "SELECT * FROM goods ";
		List goodsList = jdbcTemplate.query(sql, new ResultSetExtractor<List>() {

			public List extractData(ResultSet rs) throws SQLException, DataAccessException {
				List goodsList = new ArrayList<Customer>();
				while (rs.next()) {
					Goods goods = new Goods(rs.getString("goodsId"), rs.getString("goodsName"),
							rs.getInt("goodsQuantity"),rs.getDouble("goodsPrice"),
							rs.getString("supplierId"));
					goodsList.add(goods);
				}
				return goodsList;
			}
		});
		return goodsList;
	}
}
