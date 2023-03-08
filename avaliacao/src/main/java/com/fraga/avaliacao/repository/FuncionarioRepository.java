package com.fraga.avaliacao.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fraga.avaliacao.data.model.Funcionario;

@Repository
public class FuncionarioRepository extends RepositoryDefault<Funcionario, Integer> {

//	public List<Funcionario> getByCurso(String entityName, Integer curso_codigo){
//		List<Funcionario> entities = new ArrayList<>();
//		try {
//			PreparedStatement stmt = openConnection("SELECT * FROM " + entityName +" WHERE curso_codigo = ?");
//			stmt.setInt(1, curso_codigo);
//			ResultSet rs = stmt.executeQuery();
//			entities = extrairList(rs);
//			rs.close();
//			stmt.close();
//			mySQLConnection.closeConnection();
//		} catch (SQLException e) {
//			throw new SQLPersistenceException(e.getMessage());
//		}
//		return entities;
//	}

	@Override
	public List<Funcionario> extrairList(ResultSet rs) throws SQLException {
		List<Funcionario> funcionarios = new ArrayList<>();
		while (rs.next()) {
			funcionarios.add(new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
					rs.getString(5), rs.getDate(6), rs.getBoolean(7)));
		}
		funcionarios.forEach(t -> System.out.println(t.toString()));
		return funcionarios;
	}

	@Override
	public Funcionario extrairEntity(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return (new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
					rs.getDate(6), rs.getBoolean(7)));
		}
		return null;
	}

	@Override
	public PreparedStatement setUpdateParameters(PreparedStatement stmt, Funcionario funcionario) throws SQLException {

		stmt.setString(1, funcionario.getNome());
		stmt.setString(2, funcionario.getCpf());
		stmt.setDate(3, funcionario.getNascimento());
		stmt.setString(4, funcionario.getCargo());
		stmt.setDate(5, funcionario.getAdmissao());
		stmt.setBoolean(6, funcionario.getStatus());
		stmt.setInt(7, funcionario.getCodigo());
		return stmt;

	}

	@Override
	public PreparedStatement setCreateParameters(PreparedStatement stmt, Funcionario funcionario) throws SQLException {
		stmt.setString(1, funcionario.getNome());
		stmt.setString(2, funcionario.getCpf());
		stmt.setDate(3, funcionario.getNascimento());
		stmt.setString(4, funcionario.getCargo());
		stmt.setDate(5, funcionario.getAdmissao());
		stmt.setBoolean(6, funcionario.getStatus());
		return stmt;
	}
}
