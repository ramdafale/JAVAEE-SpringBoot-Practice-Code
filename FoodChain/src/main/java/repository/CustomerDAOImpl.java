package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class CustomerDAOImpl implements CustomerDAO {

	private DataSource dataSource;

	@Override
	public int addCustomer(int customerId, String customerName,
			String customerAddress, String paymentMode) {
		
		 try {
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		Connection connection = null;
		int addData = 0;
		try {
			 String url = "jdbc:mysql://localhost:3306/mydb1";
			 System.out.println("Befotr get conn");
			connection = DriverManager.getConnection(url,"root","root");
			System.out.println("connection:::::"+connection);
			System.out.println("customerName :"+customerName);
					
			
			Statement statement = connection.createStatement();
			addData = statement.executeUpdate("INSERT INTO Customer values(customerId,customerName,customerAddress,paymentMode)");
			System.out.println("addData "+addData);
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
