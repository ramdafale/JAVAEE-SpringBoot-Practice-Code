package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class CustomerDAOImpl implements CustomerDAO {

	private DataSource dataSource;

	@Override
	public int addCustomer(int customerId, String customerName,
			String customerAddress, String paymentMode) {
		Connection connection = null;
		int addData = 0;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			addData = statement.executeUpdate("INSERT INTO Customer values(customerId,customerName,customerAddress,paymentMode)");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return addData;

	}

	@Override
	public String removeCustomer(int customerId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "DELETE FROM Customer where customerId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data deleted";

	}

	@Override
	public String updateCustomer(int customerId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "UPDATE Customer set paymentMode='Cash' where customerId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data updated";
	}

}
