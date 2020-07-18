package com.rafael.ibltelecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafael.ibltelecom.domain.enums.FormaPagamento;
import com.rafael.ibltelecom.domain.enums.TipoCliente;
import com.rafael.ibltelecom.domain.enums.Vencimento;

@Entity
public class ClientePlano implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String rg;
	
	@Column(unique = true)
	private String cpfOuCnpj;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	private String email;
	
	private Integer tipo;
	
	private Integer formaPagamento;
	
	private Integer vencimento;
	
	@ManyToOne
	@JoinColumn(name = "logadouro_id")
	private Logradouro logradouro;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Plano planos;
	
	public ClientePlano() {		
	}	

	public ClientePlano(Integer id, String nome, String rg, String cpfOuCnpj, Date dataNascimento,
			String email, TipoCliente tipo, Logradouro logradouro, Plano planos,
			FormaPagamento formaPagamento, Vencimento vencimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpfOuCnpj = cpfOuCnpj;
		this.dataNascimento = dataNascimento;		
		this.email = email;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.logradouro = logradouro;
		this.planos = planos;
		this.formaPagamento = (formaPagamento == null) ? null : formaPagamento.getCod();
		this.vencimento = (vencimento == null) ? null : vencimento.getCod();
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

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}		

	public FormaPagamento getFormaPagamento() {
		return FormaPagamento.toEnum(formaPagamento);
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}	

	public Vencimento getVencimento() {
		return Vencimento.toEnum(vencimento);
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento.getCod();
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	
	public Plano getPlanos() {
		return planos;
	}

	public void setPlanos(Plano planos) {
		this.planos = planos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		ClientePlano other = (ClientePlano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
