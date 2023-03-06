package com.fraga.avaliacao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.persistencia.SQLConnection;

@Repository
public class CursoRepository {

	@Autowired
	private SQLConnection mySQLConnection;

	private PreparedStatement openConnection(String sql) throws SQLException {
		PreparedStatement stmt;
		Connection conn = mySQLConnection.getConnection();
		stmt = conn.prepareStatement(sql);
		return stmt;
	}

	public List<Curso> getAll() throws SQLException {
		PreparedStatement stmt = openConnection("SELECT * FROM curso");
		ResultSet rs = stmt.executeQuery();
		List<Curso> cursos = new ArrayList<>();
		while (rs.next()) {
			cursos.add(new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
		}
		rs.close();
		stmt.close();
		mySQLConnection.closeConnection();
		return cursos;
	}

	public Optional<Curso> getById(Integer codigo) throws SQLException {
		PreparedStatement stmt = openConnection("SELECT * FROM curso WHERE codigo = ?");
		stmt.setInt(1, codigo);
		ResultSet rs = stmt.executeQuery();
		var entity = new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		Optional<Curso> optionalCurso = Optional.of(entity);
		return optionalCurso;
	}

	public Optional<Curso> get(Curso curso) throws SQLException {
		PreparedStatement stmt = openConnection("SELECT * FROM curso WHERE nome = ?, duracao = ? ");
		stmt.setString(1, curso.getNome());
		stmt.setInt(2, curso.getDuracao());
		ResultSet rs = stmt.executeQuery();
		var entity = new Curso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
		Optional<Curso> optionalCurso = Optional.of(entity);
		return optionalCurso;
	}

	public Curso save(Curso curso)  throws Exception, SQLException{
		PreparedStatement stmt = openConnection("INSERT INTO curso (nome, descricao, duracao) values (?,?,?)");
		stmt.setString(2, curso.getNome());
		stmt.setString(3, curso.getDescricao());
		stmt.setInt(4, curso.getDuracao());
		stmt.executeUpdate();
		stmt.close();
		return get(curso)
				.orElseThrow(() -> new Exception(""));
		
	}
	
	public void delete(Integer codigo) {
		
	}

}
