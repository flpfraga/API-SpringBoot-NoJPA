package com.fraga.avaliacao.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLConnection {

	Connection getConnection();

	void closeConnection() throws SQLException;

}