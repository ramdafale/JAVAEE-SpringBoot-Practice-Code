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
	public List<Supplier> viewSupplier(final int supplierId) {
		// TODO Auto-generated method stub
		List<Supplier> list = (List<Supplier>) jdbcTemplate.query(
				"SELECT * FROM supplier where supplierID  = '" + supplierId + "' ",
				new BeanPropertyRowMapper<Supplier>(Supplier.class));
		System.out.println("In supplier view");
		return list;
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
