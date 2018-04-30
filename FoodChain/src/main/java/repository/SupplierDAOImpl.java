package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class SupplierDAOImpl implements SupplierDAO {

	private DataSource dataSource;

	@Override
	public int addSupplier(int supplierId, String supplierName,
			String supplierAddress, int quantityOrder, int orderId,
			double amount) {

		Connection connection = null;
		int addData = 0;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			addData = statement.executeUpdate("INSERT INTO Supplier values(supplierId,supplierName,supplierAddress,quantityOrder,orderId,amount)");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return addData;
	}

	@Override
	public String removeSupplier(int supplierId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "DELETE FROM Supplier where supplierId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, supplierId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data deleted";
	}

	@Override
	public String updateSupplier(int supplierId) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			String sql = "UPDATE Supplier set quantityOrder=1 where supplierId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, supplierId);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return "Data updated";
	}

}
