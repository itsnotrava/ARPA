package dao;

import exception.EmailAlreadyTaken;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UtenzeDao {

	private final Connection connection;

	public UtenzeDao() throws SQLException {
		this.connection = new ConnectionFactory().createConnection("sqlite");
	}

	private boolean checkEmail(String email) throws SQLException {
		String sql = "SELECT id FROM Utenze WHERE email=?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getInt(1) != 0;
	}

	public void insertUtenza(String email, String password) throws SQLException, EmailAlreadyTaken {
		// Controlla che la email non esista già
		if (!checkEmail(email)) {
			throw new EmailAlreadyTaken();
		}

		String sql = "INSERT INTO Utenze (email, password) VALUES (?, ?)";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);

		preparedStatement.execute();
	}

	public boolean verifyUtenza(String email, String password) throws SQLException {
		String sql = "SELECT id FROM Utenze WHERE email=? AND password=?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);

		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getInt(1) != 0;
	}

	/**
	 * Metodo che da email e password returna 0 se l'utente non esiste,
	 * e il suo ID se invece esiste.
	 * @param email
	 * @param password
	 * @return id [0, *]
	 * @throws SQLException
	 */
	public int getIdFromUtenza(String email, String password) throws SQLException {
		String sql = "SELECT id FROM Utenze WHERE email=? AND password=?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);

		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getInt(1);
	}

	public void deleteUtenza(int id) throws SQLException {
		String sql = "DELETE FROM Utenze WHERE id=?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);

		preparedStatement.execute();
	}

}
