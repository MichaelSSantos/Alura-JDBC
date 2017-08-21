package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionPoolSingleton {

	private DataSource dataSource;
	private static ConnectionPoolSingleton uniqueInstance;

	public static synchronized ConnectionPoolSingleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ConnectionPoolSingleton();
		}
		return uniqueInstance;
	}
	
	public ConnectionPoolSingleton() {
		MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
		pool.setUrl("jdbc:mysql://localhost:3306/lojavirtual");
		pool.setUser("root");
		pool.setPassword("ms1002");
		this.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
