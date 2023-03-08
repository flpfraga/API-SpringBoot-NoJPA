package com.fraga.avaliacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.avaliacao.converter.DozerConverter;
import com.fraga.avaliacao.data.dao.FuncionarioDAO;
import com.fraga.avaliacao.data.dao.TurmaDAO;
import com.fraga.avaliacao.data.model.Curso;
import com.fraga.avaliacao.data.model.Funcionario;
import com.fraga.avaliacao.data.model.Turma;
import com.fraga.avaliacao.exception.ResourceNotFoundException;
import com.fraga.avaliacao.repository.CursoRepository;
import com.fraga.avaliacao.repository.FuncionarioRepository;
import com.fraga.avaliacao.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private final String entityName = "turma";

	public List<TurmaDAO> getAll() {

		List<Turma> turmas = repository.getAll(entityName);
		return DozerConverter.parseList(turmas, TurmaDAO.class);

	}
	public List<TurmaDAO> getByCurso(Integer curso_codigo) {
		
		Curso curso = cursoRepository.getById(curso_codigo, "curso")
				.orElseThrow(() -> new ResourceNotFoundException("Not matches for this course code."));
		
		List<Turma> turmas = repository.getByCurso(entityName, curso_codigo);
		return DozerConverter.parseList(turmas, TurmaDAO.class);
		
	}

	public TurmaDAO getById(Integer codigo) {

		Turma entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		return DozerConverter.parseObject(entity, TurmaDAO.class);
	}

	public void create(TurmaDAO turmaDAO, Integer cursoCodigo) {
		
		Curso curso = cursoRepository.getById(cursoCodigo, "curso")
				.orElseThrow(() -> new ResourceNotFoundException("Not matches for this course code."));
		
		Turma turma = DozerConverter.parseObject(turmaDAO, Turma.class);
		
		turma.setCurso_codigo(curso.getCodigo());

		repository.save(turma, entityName);
	}

	public void delete(Integer codigo) {
		Turma turma = repository.getById(codigo, entityName).orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		repository.delete(codigo, entityName);
	}

	public void update(TurmaDAO turmaDAO, Integer codigo) {

		Turma entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		entity.setInicio(turmaDAO.getInicio());
		entity.setFim(turmaDAO.getFim());
		entity.setLocal(turmaDAO.getLocal());

		repository.update(entity, entityName);
	}
	
	
	public void includeParticipante(Integer turma_codigo, Integer funcionario_codigo) {
		repository.getById(turma_codigo, entityName)
		.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		
		funcionarioRepository.getById(funcionario_codigo, "funcionario")
		.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		
		repository.includeParticipante(turma_codigo, funcionario_codigo);
	}
	
	public List<FuncionarioDAO> getParticipanteByTurma(Integer turma_codigo){
		repository.getById(turma_codigo, entityName)
		.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		
		return DozerConverter.parseList(repository.getParticipanteByTurma(turma_codigo), FuncionarioDAO.class);
		
	}

}
