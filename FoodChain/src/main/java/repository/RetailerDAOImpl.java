/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.Driver;

import model.Goods;

/**
 * @author trainee
 *
 */
public class RetailerDAOImpl implements RetailerDAO {

	@Override
	public void viewGoods() {
		try {
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
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
			
			
			
			Statement statement = connection.createStatement();
			addData = statement.executeUpdate("select * from Goods)");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}	
		
		
		
		
		
		

		
		
	}

	@Override
	public String viewCustomer(int customerId) {
		
		
		try {
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
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
			
			
			
			Statement statement = connection.createStatement();
			addData = statement.executeUpdate("select * from Goods)");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return addData;	
		
		
		
		
	}

	@Override
	public String viewSupplier(int supplierId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
}
