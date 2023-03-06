package com.fraga.avaliacao.data.model;

import java.io.Serializable;
import java.util.Objects;

public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	private String nome;
	
	private String descricao;
	
	private Integer duracao;
	
	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(Integer codigoInteger, String nome, String descricao, Integer duracao) {
		super();
		this.codigo = codigoInteger;
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

	@Override
	public String toString() {
		return "Curso [codigoInteger=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", duracao="
				+ duracao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, descricao, duracao, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(duracao, other.duracao) && Objects.equals(nome, other.nome);
	}
	

	
	
}
