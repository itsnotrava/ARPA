package factory;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.sqlite.SQLiteDataSource;

/**
 * <h2>Connection Factory</h2>
 * <p>Questa classe crea e restituisce connessioni al DB, a seconda del tipo di DB:</p>
 * <ul>
 * <li>SQlite</li>
 * <li>MySQL</li>
 * </ul>
 * by @NicolaTravaglini
 */
public class ConnectionFactory {
	public Connection createConnection(String tipoDatabase) throws SQLException {
		switch (tipoDatabase) {
			case "sqlite" -> {
				SQLiteDataSource dataSource = new SQLiteDataSource();
				dataSource.setUrl("jdbc:sqlite:identifier.sqlite");
				return dataSource.getConnection();
			}
			case "mysql" -> {
				MysqlDataSource dataSource = new MysqlDataSource();
				dataSource.setUrl("jdbc:mysql://localhost/ARPA?user=root");
				return dataSource.getConnection();
			}
			default -> {
				return null;
			}
		}
	}
}
