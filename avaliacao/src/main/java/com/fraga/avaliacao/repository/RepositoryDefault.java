package com.fraga.avaliacao.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fraga.avaliacao.config.SqlPropertiesConfig;
import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.exception.SQLPersistenceException;
import com.fraga.avaliacao.persistencia.SQLConnection;

public abstract class RepositoryDefault<D, T> {

	@Autowired
	protected SQLConnection mySQLConnection;
	
	@Autowired
	protected SqlPropertiesConfig properties;
	
	private String entityName;
	

	protected PreparedStatement openConnection(String sql) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn = mySQLConnection.getConnection(properties);
		stmt = conn.prepareStatement(sql);
		return stmt;
	}
	
	public abstract List<D> extrairList(ResultSet rs) throws SQLException;
	
	public abstract D extrairEntity(ResultSet rs) throws SQLException;
	
	public abstract PreparedStatement setCreateParameters(PreparedStatement stmt, D entity) throws SQLException;
	
	public abstract PreparedStatement setUpdateParameters(PreparedStatement stmt, D entity) throws SQLException;
	
	public List<D> getAll(String entityName) {
		List<D> entities = new ArrayList<>();
		try {
			PreparedStatement stmt = openConnection("SELECT * FROM " + entityName);
			ResultSet rs = stmt.executeQuery();
			entities = extrairList(rs);
			rs.close();
			stmt.close();
			mySQLConnection.closeConnection();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}
		return entities;
	}
	
	public Optional<D> getById(Integer codigo, String entityName) {
		D entity;
		
		try {
			PreparedStatement stmt = openConnection("SELECT * FROM "+entityName+" WHERE codigo = ?");
			stmt.setInt(1, codigo);
			ResultSet rs = stmt.executeQuery();
			entity = extrairEntity(rs);
			rs.close();
			stmt.close();
			mySQLConnection.closeConnection();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}

		Optional<D> optionalCurso = Optional.ofNullable(entity);
		
		return optionalCurso;
	}
	
	public void delete(Integer codigo, String entityName) {
		try {
			PreparedStatement stmt = openConnection("DELETE from "+entityName+" WHERE codigo = ?");
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}
	}
	
	public void save(D entity, String entityName) {
		try {
			String sql = createParamsQuery(entity);
			System.out.println(sql);
			PreparedStatement stmt = openConnection("INSERT INTO "+entityName+ ""+sql+"");
			stmt = setCreateParameters(stmt, entity);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}

	}
	
	public void update(D entity, String entityName) {
		try {
			String sql = updateParamsQuery(entity);
			PreparedStatement stmt = openConnection(
					"UPDATE curso set "+sql+" WHERE codigo = ?");
			stmt = setUpdateParameters(stmt, entity);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}
	}
	
	private String createParamsQuery(D entity) {
		Field [] fields = entity.getClass().getDeclaredFields();
		StringBuilder sqlPathDescription = new StringBuilder();
		StringBuilder sqlPathPathValue = new StringBuilder();
		sqlPathDescription.append("(");
		sqlPathPathValue.append("(");
		for(int c = 2 ; c < fields.length;c++) {
			sqlPathDescription.append(fields[c].getName());
			sqlPathPathValue.append("?");
			if(fields.length-c <=1) {
				sqlPathDescription.append(") ");
				sqlPathPathValue.append(") ");
			}
			else {
				sqlPathDescription.append(", ");
				sqlPathPathValue.append(", ");
			}
		}
		return sqlPathDescription.toString()+" values "+sqlPathPathValue.toString();
	}
	private String updateParamsQuery(D entity) {
		Field [] fields = entity.getClass().getDeclaredFields();
		StringBuilder sqlPath = new StringBuilder();
		
	
		for(int c = 2 ; c < fields.length;c++) {
			sqlPath.append(fields[c].getName());
			sqlPath.append("= ?");
			if(fields.length-c >1) {
				sqlPath.append(", ");
			}
			
		}
		return sqlPath.toString();
	}
	
	
	
}