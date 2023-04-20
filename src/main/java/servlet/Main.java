package servlet;

import dao.UtenzeDao;
import exception.EmailAlreadyTaken;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException, EmailAlreadyTaken {
		UtenzeDao utenzeDao = new UtenzeDao(); // Data Access Object
		utenzeDao.insertUtenza("ciao", "ciao");
	}
}
