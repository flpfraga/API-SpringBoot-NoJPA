package com.fraga.avaliacao.data.dao;

import java.sql.Date;
import java.time.LocalDate;

public class TurmaDAO {

	private Integer codigo;

	private Date inicio;

	private Date fim;

	private String local;
	
	private Integer curso_codigo;
	
	public TurmaDAO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getCurso_codigo() {
		return curso_codigo;
	}

	public void setCurso_codigo(Integer curso_codigo) {
		this.curso_codigo = curso_codigo;
	}

	@Override
	public String toString() {
		return "TurmaDAO [codigo=" + codigo + ", inicio=" + inicio + ", fim=" + fim + ", local=" + local + "]";
	}
	

}
