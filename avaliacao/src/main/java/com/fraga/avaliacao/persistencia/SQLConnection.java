package com.fraga.avaliacao.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

import com.fraga.avaliacao.config.SqlPropertiesConfig;

public interface SQLConnection {

	Connection getConnection(SqlPropertiesConfig properties);
	
	void closeConnection() throws SQLException;

}