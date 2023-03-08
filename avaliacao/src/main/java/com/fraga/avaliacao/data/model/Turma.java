package com.fraga.avaliacao.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

public class Turma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codigo;

	private Date inicio;

	private Date fim;

	private String local;

	private Integer curso_codigo;

	public Turma() {
		// TODO Auto-generated constructor stub
	}

	public Turma(Integer codigo, Date inicio, Date fim, Integer curso_codigo, String local) {
		super();
		this.codigo = codigo;
		this.inicio = inicio;
		this.fim = fim;
		this.local = local;
		this.curso_codigo = curso_codigo;
	}

	@Override
	public String toString() {
		return "Turma [codigo=" + codigo + ", inicio=" + inicio + ", fim=" + fim + ", local=" + local + ", curso_codigo="
				+ curso_codigo + "]";
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
	public int hashCode() {
		return Objects.hash(codigo, fim, inicio, local);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(fim, other.fim)
				&& Objects.equals(inicio, other.inicio) && Objects.equals(local, other.local);
	}

}
