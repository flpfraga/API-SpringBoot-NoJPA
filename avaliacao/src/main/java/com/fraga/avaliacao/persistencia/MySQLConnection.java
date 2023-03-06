package com.fraga.avaliacao.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fraga.avaliacao.config.SqlPropertiesConfig;

@Component
public class MySQLConnection implements SQLConnection {
	
	private Connection connection;

	public MySQLConnection(SqlPropertiesConfig properties){
        try {
        	connection = DriverManager.getConnection(properties.getUrl(), properties.getUsername(), properties.getPassword());
		} catch (SQLException e) {
			System.out.println("Erro ao tentar conectar ao banco de dados. " + e.getMessage());
			e.printStackTrace();
		}
    }
    
    @Override
	public Connection getConnection() {
        return connection;
    }

    @Override
	public void closeConnection() throws SQLException {
        connection.close();
    }
}
