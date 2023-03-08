package com.fraga.avaliacao.data.dao;

import java.io.Serializable;

import com.github.dozermapper.core.Mapping;

public class CursoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Mapping("codigo")
	private Integer key;
	
	private String nome;
	
	private String descricao;
	
	private Integer duracao;
	
	
	public CursoDAO() {
		// TODO Auto-generated constructor stub
	}

	
	public Integer getCodigo() {
		return key;
	}

	public void setCodigo(Integer key) {
		this.key = key;
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
