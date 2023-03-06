package com.fraga.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraga.avaliacao.CursoService;
import com.fraga.avaliacao.data.dao.CursoDAO;

@RestController
@RequestMapping("/api/curso/v1")
public class CursoController {
	
	@Autowired
	private CursoService service;
	
	@GetMapping()
	public List<CursoDAO> getAll() throws Exception {
		return service.getAll();
	}
	

}
