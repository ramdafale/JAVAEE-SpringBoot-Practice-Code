package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Ram
 *
 */
@Repository()
@Qualifier("GoodsDAO")
public class GoodsDAOImpl implements GoodsDAO {

	// private DataSource dataSource;

	// private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	/**
	 *   configuring a jdbc template
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate1(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity, double goodsPrice) {
		String query = "insert into goods values(" + goodsId + "," + "'" + goodsName + "'" + "," + goodsQuantity + ","
				+ goodsPrice + ")";
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

		String query = "delete from goods where goodsId=" + goodsId;
		System.out.println(query);
		return jdbcTemplate.update(query);
	}

	/*
	 * Connection connection = null; int addData = 0;
	 * 
	 * try {
	 * 
	 * connection = dataSource.getConnection(); Statement statement =
	 * connection.createStatement();
	 * 
	 * addData = statement
	 * .executeUpdate("INSERT INTO Goods values(goodsId,goodsName,goodsQuantity,goodsPrice)"
	 * );
	 * 
	 * statement.close(); connection.close(); } catch (SQLException e) {
	 * System.out.println(e); }
	 * 
	 * return addData; }
	 * 
	 * public String removeGoods(int goodsId) { Connection connection = null; try {
	 * connection = dataSource.getConnection(); String sql =
	 * "DELETE FROM Goods where goodsId=?"; PreparedStatement statement =
	 * connection.prepareStatement(sql); statement.setInt(1, goodsId);
	 * statement.executeUpdate(); statement.close(); connection.close(); } catch
	 * (SQLException e) { System.out.println(e); } return "Data deleted"; }
	 * 
	 * public String updateGoods(int goodsId) { Connection connection = null; try {
	 * connection = dataSource.getConnection(); String sql =
	 * "UPDATE Goods set goodsQuantity=1 where goodsId=?"; PreparedStatement
	 * statement = connection.prepareStatement(sql); statement.setInt(1, goodsId);
	 * statement.executeUpdate(); statement.close(); connection.close(); } catch
	 * (SQLException e) { System.out.println(e); } return "Data updated"; }
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
