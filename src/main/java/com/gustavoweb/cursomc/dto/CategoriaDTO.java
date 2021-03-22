package com.gustavoweb.cursomc.dto;

import java.io.Serializable;

import com.gustavoweb.cursomc.domain.Categoria;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="preenchimento obrigatório.")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres.")
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria obj) {
		this.nome = obj.getNome();
		this.id = obj.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
