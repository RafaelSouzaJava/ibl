package com.rafael.ibltelecom.dto;

import java.io.Serializable;

import com.rafael.ibltelecom.domain.Bairro;



public class BairroDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String bairro;
	
	public BairroDTO() {		
	}
	
	public BairroDTO(Bairro obj) {
		this.id = obj.getId();
		this.bairro = obj.getBairro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
