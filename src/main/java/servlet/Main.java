package servlet;

import dao.UtenzeDao;
import exception.EmailAlreadyTakenException;

import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException, EmailAlreadyTakenException {
		UtenzeDao utenzeDao = new UtenzeDao(); // Data Access Object
		utenzeDao.insertUtenza("ciaoc", "ciao");
	}
}
