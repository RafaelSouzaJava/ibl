package com.rafael.ibltelecom.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Plano implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String download;
	
	private String upload;	
	
	private Double mensalidade;
	
	private Double instalacao;
	
	private String promocao;
	
	private String fidelidade;
	
	private String detalhes;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PLANOS_CATEGORIA",
		joinColumns = @JoinColumn(name = "planos_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
			)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "internet")
	private List<Combo> combos = new ArrayList<>();
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "planos")	
	private List<ClientePlano> cliente = new ArrayList<>();
	
	public Plano() {		
	}

	public Plano(Integer id, String nome, String download, String upload, Double mensalidade, Double instalacao,
			String promocao, String fidelidade, String detalhes) {
		super();
		this.id = id;
		this.nome = nome;
		this.download = download;
		this.upload = upload;
		this.mensalidade = mensalidade;
		this.instalacao = instalacao;
		this.promocao = promocao;
		this.fidelidade = fidelidade;
		this.detalhes = detalhes;
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

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public Double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Double mensalidade) {
		this.mensalidade = mensalidade;
	}

	public Double getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(Double instalacao) {
		this.instalacao = instalacao;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getFidelidade() {
		return fidelidade;
	}

	public void setFidelidade(String fidelidade) {
		this.fidelidade = fidelidade;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}	
	
	@JsonIgnore
	public List<Combo> getCombos() {
		return combos;
	}

	public void setCombos(List<Combo> combos) {
		this.combos = combos;
	}
		
	
	public List<ClientePlano> getCliente() {
		return cliente;
	}

	public void setCliente(List<ClientePlano> cliente) {
		this.cliente = cliente;
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
		Plano other = (Plano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
