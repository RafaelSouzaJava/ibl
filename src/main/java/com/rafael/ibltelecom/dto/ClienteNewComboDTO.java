package com.rafael.ibltelecom.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.ibltelecom.services.validation.ClienteComboInsert;

@ClienteComboInsert
public class ClienteNewComboDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max= 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")	
	private String rg;

	private String cpfOuCnpj;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNascimento;	

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message ="E-mail invalido")
	private String email;

	private Integer tipo;
	
	private Integer formaPagamento;
	
	private Integer vencimento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	
	private Integer logradouroId;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String cep;
	

	private Integer bairroId;
	
	private Integer combosId;
	
	
	public ClienteNewComboDTO() {		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getLogradouroId() {
		return logradouroId;
	}

	public void setLogradouroId(Integer logradouroId) {
		this.logradouroId = logradouroId;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getBairroId() {
		return bairroId;
	}

	public void setBairroId(Integer bairroId) {
		this.bairroId = bairroId;
	}	

	public Integer getCombosId() {
		return combosId;
	}

	public void setCombosId(Integer combosId) {
		this.combosId = combosId;
	}

	public Integer getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Integer formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getVencimento() {
		return vencimento;
	}

	public void setVencimento(Integer vencimento) {
		this.vencimento = vencimento;
	}

}
