package wasidevestak.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


	public static Connection getConnection() {

		try {

			return DriverManager.getConnection("jdbc:mysql://localhost/avaliacao_dsi", "root", "3002403") ;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
