package com.fraga.avaliacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraga.avaliacao.converter.DozerConverter;
import com.fraga.avaliacao.data.dao.FuncionarioDAO;
import com.fraga.avaliacao.data.model.Funcionario;
import com.fraga.avaliacao.exception.ResourceNotFoundException;
import com.fraga.avaliacao.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	
	private final String entityName = "funcionario";

	public List<FuncionarioDAO> getAll() {

		List<Funcionario> funcionarios = repository.getAll(entityName);
		return DozerConverter.parseList(funcionarios, FuncionarioDAO.class);

	}
//	public List<FuncionarioDAO> getByCurso(Integer curso_codigo) {
//		
//		Curso curso = cursoRepository.getById(curso_codigo, "curso")
//				.orElseThrow(() -> new ResourceNotFoundException("Not matches for this course code."));
//		
//		List<Funcionario> funcionarios = repository.getByCurso(entityName, curso_codigo);
//		return DozerConverter.parseList(funcionarios, FuncionarioDAO.class);
//		
//	}

	public FuncionarioDAO getById(Integer codigo) {

		Funcionario entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		return DozerConverter.parseObject(entity, FuncionarioDAO.class);
	}

	public void create(FuncionarioDAO funcionarioDAO) {
		
		Funcionario funcionario = DozerConverter.parseObject(funcionarioDAO, Funcionario.class);
		
		repository.save(funcionario, entityName);
	}

	public void delete(Integer codigo) {
		Funcionario funcionario = repository.getById(codigo, entityName).orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));
		repository.delete(codigo, entityName);
	}

	public void update(FuncionarioDAO funcionarioDAO, Integer codigo) {

		Funcionario entity = repository.getById(codigo, entityName)
				.orElseThrow(() -> new ResourceNotFoundException("Not matcher for this code!"));

		entity.setNome(funcionarioDAO.getNome());
		entity.setCpf(funcionarioDAO.getCpf());
		entity.setNascimento(funcionarioDAO.getNascimento());
		entity.setCargo(funcionarioDAO.getCargo());
		entity.setAdmissao(funcionarioDAO.getAdmissao());
		entity.setStatus(funcionarioDAO.getStatus());

		repository.update(entity, entityName);
	}

}
