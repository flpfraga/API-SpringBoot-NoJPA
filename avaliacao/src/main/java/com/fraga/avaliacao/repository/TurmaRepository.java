package com.fraga.avaliacao.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fraga.avaliacao.data.model.Funcionario;
import com.fraga.avaliacao.data.model.Turma;
import com.fraga.avaliacao.exception.SQLPersistenceException;

@Repository
public class TurmaRepository extends RepositoryDefault<Turma, Integer> {


	public List<Turma> getByCurso(String entityName, Integer curso_codigo){
		List<Turma> entities = new ArrayList<>();
		try {
			PreparedStatement stmt = openConnection("SELECT * FROM " + entityName +" WHERE curso_codigo = ?");
			stmt.setInt(1, curso_codigo);
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
	
	@Override
	public List<Turma> extrairList(ResultSet rs) throws SQLException{
		List<Turma> turmas = new ArrayList<>();
		while (rs.next()) {
			turmas.add(new Turma(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5)));
		}
		turmas.forEach(t -> System.out.println(t.toString()));
		return turmas;
	}

	@Override
	public Turma extrairEntity(ResultSet rs) throws SQLException{
		if (rs.next()) {
			return (new Turma(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4), rs.getString(5)));
		}
		return null;
	}
	
	@Override
	public PreparedStatement setCreateParameters(PreparedStatement stmt, Turma turma) throws SQLException {

		stmt.setDate(1, turma.getInicio());
		stmt.setDate(2, turma.getFim());
		stmt.setString(3, turma.getLocal());
		stmt.setInt(4, turma.getCurso_codigo());
		return stmt;
		
	}
	@Override
	public PreparedStatement setUpdateParameters(PreparedStatement stmt, Turma turma) throws SQLException {
		
		stmt.setDate(1, turma.getInicio());
		stmt.setDate(2, turma.getFim());
		stmt.setString(3, turma.getLocal());
		stmt.setInt(4, turma.getCurso_codigo());
		stmt.setInt(5, turma.getCodigo());
		return stmt;
		
	}
	
	
	public void includeParticipante(Integer turma_codigo, Integer funcionario_codigo) {
		try {
			PreparedStatement stmt = openConnection("INSERT INTO turmaparticipante (turma_codigo, funcionario_codigo) value (?,?)");
			stmt.setInt(1, turma_codigo);
			stmt.setInt(2, funcionario_codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}
	}
	public List<Funcionario> getParticipanteByTurma(Integer turma_codigo) {
		List<Funcionario> funcionarios = new ArrayList<>();
		try {
			PreparedStatement stmt = openConnection("SELECT f.*\r\n"
					+ "FROM turmaparticipante tp\r\n"
					+ "INNER JOIN funcionario f ON f.codigo = tp.funcionario_codigo\r\n"
					+ "WHERE tp.turma_codigo = ?");
			stmt.setInt(1, turma_codigo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				funcionarios.add(new Funcionario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getBoolean(7)));
			}
			rs.close();
			stmt.close();
			mySQLConnection.closeConnection();
		} catch (SQLException e) {
			throw new SQLPersistenceException(e.getMessage());
		}
		return funcionarios;
	}
}
