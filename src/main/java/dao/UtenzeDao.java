package dao;

import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtenzeDao {
	private final Connection connection;

	public UtenzeDao() throws SQLException {
		this.connection = new ConnectionFactory().createConnection("mysql");
	}

	public void insertUtenza(String email, String password) throws SQLException {
		String sql = "INSERT INTO Utenze (email, password) VALUES (?, ?)";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);

		preparedStatement.execute();
	}
}
