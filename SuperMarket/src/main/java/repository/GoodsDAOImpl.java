package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Ram
 *
 */
public class GoodsDAOImpl implements GoodsDAO {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) 
	{
		String query = "insert into goods values(" +goodsId+","+ "'"+goodsName +"'"+","+goodsQuantity+","+ goodsPrice +")";
		System.out.println(query);
		
		return jdbcTemplate.update(query);
	}
	
	

	@Override
	public int updateGoods(int goodsId, String goodsName) {
		String query = "update goods set goodsName=" + "'" + goodsName + "'" + " where goodsId=" + goodsId;
		// String query = "update customer set customerName="+name+" where customerId="+
		// customerID;

		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	@Override
	public int removeGoods(int goodsId) {
		
		String query = "delete from goods where goodsId="+goodsId;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}


		/*
		Connection connection = null;
		int addData = 0;
		
		try {
			
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			
			addData = statement
					.executeUpdate("INSERT INTO Goods values(goodsId,goodsName,goodsQuantity,goodsPrice)");
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return addData;
	}

	public String removeGoods(int goodsId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "DELETE FROM Goods where goodsId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, goodsId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data deleted";
	}

	public String updateGoods(int goodsId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "UPDATE Goods set goodsQuantity=1 where goodsId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, goodsId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data updated";
	}
*/
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



}
