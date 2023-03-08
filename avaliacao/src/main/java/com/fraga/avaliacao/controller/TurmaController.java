package com.fraga.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.avaliacao.data.dao.FuncionarioDAO;
import com.fraga.avaliacao.data.dao.TurmaDAO;
import com.fraga.avaliacao.service.TurmaService;

@RestController
@RequestMapping("/api/turma/v1")
public class TurmaController {
	
	@Autowired
	private TurmaService service;
	
	@GetMapping()
	public ResponseEntity<List<TurmaDAO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("curso/{curso_codigo}")
	public ResponseEntity<List<TurmaDAO>> getByCurso(@PathVariable("curso_codigo") Integer curso_codigo) {
		return ResponseEntity.ok(service.getByCurso(curso_codigo));
	}
	
	@GetMapping("/code/{codigo}")
	public ResponseEntity<TurmaDAO> getById(@PathVariable("codigo") Integer codigo) {
		return ResponseEntity.ok(service.getById(codigo));
	}

	@PostMapping("code/{curso_codigo}")
	public ResponseEntity<?> create(
			@PathVariable("curso_codigo") Integer cursoCodigo
			,@RequestBody TurmaDAO turmaDAO) {
		service.create(turmaDAO, cursoCodigo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/code/{codigo}")
	public ResponseEntity<TurmaDAO> update(
			@PathVariable("codigo") Integer codigo
			,@RequestBody TurmaDAO turmaDAO) {
		service.update(turmaDAO, codigo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/code/{codigo}")
	public ResponseEntity<?> delete(@PathVariable("codigo") Integer codigo){
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/participante/{turma_codigo}/{funcionario_codigo}")
	public ResponseEntity<?> includeParticipante(
			@PathVariable("turma_codigo") Integer turma_codigo
			,@PathVariable("funcionario_codigo") Integer funcionario_codigo) {
		service.includeParticipante(turma_codigo, funcionario_codigo);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/participante/{turma_codigo}")
	public ResponseEntity<List<FuncionarioDAO>> getParticipanteByTurma(@PathVariable("turma_codigo") Integer turma_codigo) {
		return ResponseEntity.ok(service.getParticipanteByTurma(turma_codigo));
	}
	

}
