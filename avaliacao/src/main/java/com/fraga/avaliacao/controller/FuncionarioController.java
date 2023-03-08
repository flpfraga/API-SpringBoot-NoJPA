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
import com.fraga.avaliacao.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario/v1")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping()
	public ResponseEntity<List<FuncionarioDAO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/code/{codigo}")
	public ResponseEntity<FuncionarioDAO> getById(@PathVariable("codigo") Integer codigo) {
		return ResponseEntity.ok(service.getById(codigo));
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody FuncionarioDAO cursoDAO) {
		service.create(cursoDAO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/code/{codigo}")
	public ResponseEntity<FuncionarioDAO> update(
			@PathVariable("codigo") Integer codigo
			,@RequestBody FuncionarioDAO cursoDAO) {
		service.update(cursoDAO, codigo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/code/{codigo}")
	public ResponseEntity<?> delete(@PathVariable("codigo") Integer codigo){
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	

}
