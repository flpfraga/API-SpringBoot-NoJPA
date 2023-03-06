package com.fraga.avaliacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.avaliacao.converter.DozerConverter;
import com.fraga.avaliacao.data.dao.CursoDAO;
import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repository;
	
	
	public  List<CursoDAO> getAll() {
		List<Curso> cursos = new ArrayList<>();
		try {
			cursos = repository.getAll();
		} catch (SQLException e) {
			
		}
		
		return DozerConverter.parseList(cursos, CursoDAO.class);
	}

}
