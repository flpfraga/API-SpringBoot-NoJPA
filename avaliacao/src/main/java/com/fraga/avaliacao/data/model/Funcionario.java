package com.fraga.avaliacao.data.model;

import java.io.Serializable;
import java.sql.Date;

public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	
	private String nome;
	
	private String cpf;
	
	private Date nascimento;
	
	private String cargo;
	
	private Date admissao;
	
	private Boolean status;
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Integer codigo, String nome, String cpf, Date nascimento, String cargo, Date admissao,
			Boolean status) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cargo = cargo;
		this.admissao = admissao;
		this.status = status;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
