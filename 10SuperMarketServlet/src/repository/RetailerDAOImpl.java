/**
 * 
 */
package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import model.Customer;
import model.Goods;
import model.Supplier;

/**
 * @author trainee
 *
 */

@Repository("serviceDAO")
public class RetailerDAOImpl implements RetailerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * @Override public int viewGoods() { // TODO Auto-generated method stub String
	 * query = "select * from goods"; System.out.println(query); return
	 * jdbcTemplate.update(query);
	 * 
	 * }
	 */

	
	@Override
	public List<Customer> viewCustomer(final int customerId) {
		// TODO Auto-generated method stub
		List<Customer> list = (List<Customer>) jdbcTemplate.query(
				"SELECT * FROM customer where customerId  = '" + customerId + "' ",
				new BeanPropertyRowMapper<Customer>(Customer.class));
		System.out.println("In cust view");
		return list;
	}

	@Override
	public List<Supplier> viewSupplier(int supplierId) {
		String query = "select * from supplier where supplierId=" + supplierId;
		System.out.println(query);
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Supplier>>() {
			@Override
			public List<Supplier> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<Supplier> list = new ArrayList<Supplier>();
				while (rs.next()) {
					Supplier c = new Supplier();
					c.setSupplierId(rs.getInt(1));
					c.setSupplierName(rs.getString(2));
					c.setSupplierAddress(rs.getString(3));
					c.setQuantityOrder(rs.getInt(4));
					c.setOrderId(rs.getInt(5));
					c.setAmount(rs.getDouble(6));
					list.add(c);
					System.out.println(c.getSupplierId());
					System.out.println(c.getSupplierName());
					System.out.println(c.getSupplierAddress());
					System.out.println(c.getQuantityOrder());
					System.out.println(c.getOrderId());
					System.out.println(c.getAmount());

				}

				return list;
			}
		});

	}

	public List<Goods> viewGoods() {
		// TODO Auto-generated method stub
		List<Goods> list = (List<Goods>) jdbcTemplate.query(
				"SELECT * FROM goods",
				new BeanPropertyRowMapper<Goods>(Goods.class));
		System.out.println("In goods view");
		return list;
	}

	@Override
	public int addRetailer(Supplier s) {

		String query = "insert into retailer values (" + "'" + s.getSupplierName() + "'" + "," + "'"
				+ s.getSupplierAddress() + "'" + ")";
		System.out.println(query);
		return jdbcTemplate.update(query);
	}

}
