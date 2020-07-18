package com.rafael.ibltelecom.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafael.ibltelecom.domain.Viabilidade;



public class ViabilidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;	

	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max= 60, message = "O tamanho deve ser entre 5 a 60 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 11, max= 13, message = "O tamanho deve ser entre 11 a 13 caracteres")
	private String telefone;
	
	private String telefone2;
	
	@NotEmpty(message = "Preenchimento obrigatório")	
	private String endereco;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String bairro;
	
	public ViabilidadeDTO() {		
	}
	
	public ViabilidadeDTO(Viabilidade obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.telefone = obj.getTelefone();
		this.telefone2 = obj.getTelefone2();
		this.endereco = obj.getEndereco();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
		this.bairro = obj.getBairro();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}		

}
