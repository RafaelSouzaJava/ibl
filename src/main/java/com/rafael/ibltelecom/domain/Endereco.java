package com.rafael.ibltelecom.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String numero;

	private String complemento;

	private String cep;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClientePlano cliente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "clientecombo_id")
	private ClienteCombo clientecombo;		

	
	public Endereco() {		
	}

	public Endereco(Integer id, String numero, String complemento, String cep, ClientePlano cliente,
		ClienteCombo clientecombo) {
		super();
		this.id = id;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.cliente = cliente;	
		this.clientecombo = clientecombo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ClientePlano getCliente() {
		return cliente;
	}

	public void setCliente(ClientePlano cliente) {
		this.cliente = cliente;
	}	


	public ClienteCombo getClientecombo() {
		return clientecombo;
	}

	public void setClientecombo(ClienteCombo clientecombo) {
		this.clientecombo = clientecombo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
