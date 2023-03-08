package com.fraga.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.avaliacao.converter.DozerConverter;
import com.fraga.avaliacao.data.dao.CursoDAO;
import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.exception.ResourceNotFoundException;
import com.fraga.avaliacao.repository.CursoRepository;
import com.fraga.avaliacao.repository.TurmaRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	private final String entityName = "curso";

	public List<CursoDAO> getAll() {

		List<Curso> cursos = repository.getAll(entityName);
		return DozerConverter.parseList(cursos, CursoDAO.class);

	}

	public CursoDAO getById(Integer codigo) {

		Curso entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		return DozerConverter.parseObject(entity, CursoDAO.class);
	}

	public void create(CursoDAO cursoDAO) {
		
		Curso curso = DozerConverter.parseObject(cursoDAO, Curso.class);

		repository.save(curso, entityName);
	}

	public void delete(Integer codigo) {
		Curso curso = repository.getById(codigo, entityName).orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		repository.delete(codigo, entityName);

	}

	public void update(CursoDAO cursoDAO, Integer codigo) {

		Curso entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		entity.setNome(cursoDAO.getNome());
		entity.setDescricao(cursoDAO.getDescricao());
		entity.setDuracao(cursoDAO.getDuracao());

		repository.update(entity, entityName);
	}

}
