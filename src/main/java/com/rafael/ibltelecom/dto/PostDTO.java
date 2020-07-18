package com.rafael.ibltelecom.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Lob;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.ibltelecom.domain.Post;


public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;	

	
	@Length(min = 5, max= 30, message = "O tamanho deve ser entre 5 a 30 caracteres")
	private String titulo;	
	
	
	@Length(min = 5, max= 20, message = "O tamanho deve ser entre 5 a 20 caracteres")
	private String autor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	
	@Length(min = 5, max= 100, message = "O tamanho deve ser entre 5 a 100 caracteres")
	@Lob
	private String texto;
	
	public PostDTO() {		
	}
	
	public PostDTO(Post obj) {
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.autor = obj.getAutor();
		this.data = obj.getData();
		this.texto = obj.getTexto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalDate setData(LocalDate data) {
		return this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
		

}
