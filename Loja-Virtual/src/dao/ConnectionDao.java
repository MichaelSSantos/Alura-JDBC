package dao;

import java.sql.Connection;
import java.sql.SQLException;

abstract class ConnectionDao {

	private final Connection connection;

	protected ConnectionDao() throws Exception {
		try {
			this.connection = ConnectionPoolSingleton.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("N�O FOI POSS�VEL OBTER UMA CONEX�O." + e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
