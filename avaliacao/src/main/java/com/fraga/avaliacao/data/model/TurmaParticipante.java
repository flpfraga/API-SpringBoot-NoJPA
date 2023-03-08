package com.fraga.avaliacao.data.model;

import java.util.Objects;

public class TurmaParticipante {
	
	private Integer codigo;

	private Integer turma_codigo;
	
	private Integer funcionario_codigo;
	
	public TurmaParticipante() {
		// TODO Auto-generated constructor stub
	}

	public TurmaParticipante(Integer codigo, Integer turma_codigo, Integer funcionario_codigo) {
		super();
		this.codigo = codigo;
		this.turma_codigo = turma_codigo;
		this.funcionario_codigo = funcionario_codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTurma_codigo() {
		return turma_codigo;
	}

	public void setTurma_codigo(Integer turma_codigo) {
		this.turma_codigo = turma_codigo;
	}

	public Integer getFuncionario_codigo() {
		return funcionario_codigo;
	}

	public void setFuncionario_codigo(Integer funcionario_codigo) {
		this.funcionario_codigo = funcionario_codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, funcionario_codigo, turma_codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaParticipante other = (TurmaParticipante) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(funcionario_codigo, other.funcionario_codigo)
				&& Objects.equals(turma_codigo, other.turma_codigo);
	}
	
	

}
