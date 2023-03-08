package com.fraga.avaliacao.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.exception.SQLPersistenceException;

@Repository
public class CursoRepository extends RepositoryDefault<Curso, Integer> {
	
	@Override
	public List<Curso> getAll(String entityName){
		List<Curso> entities = new ArrayList<>();
		try {
			PreparedStatement stmt = openConnection("SELECT * FROM " + entityName);
			ResultSet rs = stmt.executeQuery();
			entities = extrairList(rs);
			for (Curso c : entities) {
				stmt = openConnection("SELECT turma.codigo FROM turma WHERE curso_codigo = ?");
				stmt.setInt(1, c.getCodigo());
				rs = stmt.executeQuery();
				List <Integer> turmas = new ArrayList<>();
				while(rs.next()) {
					turmas.add(rs.getInt(1));
				}
				c.setTurmas(turmas);
			}
			rs.close();
			stmt.close();
			mySQLConnection.closeConnection();
		} catch (SQLException e) {
			throw new SQLPersistenceException("Curso " + e.getMessage());
		}
		entities.forEach(e -> System.out.println(e.toString()));
		return entities;
	}

	
	@Override
	public List<Curso> extrairList(ResultSet rs) throws SQLException{
		List<Curso> cursos = new ArrayList<>();
		while (rs.next()) {
			cursos.add(new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		return cursos;
	}

	@Override
	public Curso extrairEntity(ResultSet rs) throws SQLException{
		if (rs.next()) {
			return (new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		return null;
	}
	

	@Override
	public PreparedStatement setCreateParameters(PreparedStatement stmt, Curso curso) throws SQLException {

		stmt.setString(1, curso.getNome());
		stmt.setString(2, curso.getDescricao());
		stmt.setInt(3, curso.getDuracao());
		return stmt;
		
	}
	@Override
	public PreparedStatement setUpdateParameters(PreparedStatement stmt, Curso curso) throws SQLException {
		
		stmt.setString(1, curso.getNome());
		stmt.setString(2, curso.getDescricao());
		stmt.setInt(3, curso.getDuracao());
		stmt.setInt(4, curso.getCodigo());
		return stmt;
		
	}

}
