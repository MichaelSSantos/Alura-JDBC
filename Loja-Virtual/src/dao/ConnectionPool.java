package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class ConnectionPool {
	
	private DataSource dataSource;
	
	public ConnectionPool() {
		MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
		pool.setUrl("jdbc:mysql://localhost:3306/lojavirtual");
		pool.setUser("root");
		pool.setPassword("ms1002");
		this.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException {
//		return DriverManager.getConnection("jdbc:mysql://localhost:3306/lojavirtual", "root", "1234");
		/*
		 * Quem retorna a conexão agora é o pool de conexão e não mais o DriveManager do JDBC.
		 * */
		return dataSource.getConnection();
	}

}
