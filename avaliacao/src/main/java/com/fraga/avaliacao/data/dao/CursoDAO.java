package com.fraga.avaliacao.data.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CursoDAO {
	
	@JsonProperty
	private Integer codigo;
	
	@JsonProperty
	private String nome;
	
	@JsonProperty
	private String descricao;
	
	@JsonProperty
	private Integer duracao;
	
	
	public CursoDAO() {
		// TODO Auto-generated constructor stub
	}

	
	
	public CursoDAO(Integer codigo, String nome, String descricao, Integer duracao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
	
}
