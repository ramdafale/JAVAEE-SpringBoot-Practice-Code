package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author Ram
 *
 */
public class GoodsDAOImpl implements GoodsDAO {

	private DataSource dataSource;

	// JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public int addGoods(int goodsId, String goodsName, int goodsQuantity,
			double goodsPrice) {

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

}
