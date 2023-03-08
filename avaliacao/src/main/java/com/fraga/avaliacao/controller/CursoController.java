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

import com.fraga.avaliacao.data.dao.CursoDAO;
import com.fraga.avaliacao.service.CursoService;

@RestController
@RequestMapping("/api/curso/v1")
public class CursoController {
	
	@Autowired
	private CursoService service;
	
	@GetMapping()
	public ResponseEntity<List<CursoDAO>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody CursoDAO cursoDAO) {
		service.create(cursoDAO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/code/{codigo}")
	public ResponseEntity<CursoDAO> update(
			@PathVariable("codigo") Integer codigo
			,@RequestBody CursoDAO cursoDAO) {
		service.update(cursoDAO, codigo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/code/{codigo}")
	public ResponseEntity<?> delete(@PathVariable("codigo") Integer codigo){
		service.delete(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	

}
