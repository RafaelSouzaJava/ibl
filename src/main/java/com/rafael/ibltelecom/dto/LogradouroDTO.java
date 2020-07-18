package com.rafael.ibltelecom.dto;

import com.rafael.ibltelecom.domain.Logradouro;

public class LogradouroDTO {
	
	private Integer id;
	private String logradouro;
	
	public LogradouroDTO() {		
	}
	
	public LogradouroDTO(Logradouro obj) {
		this.id =obj.getId();
		this.logradouro = obj.getLogradouro();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	

}
