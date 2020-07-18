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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Combo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	
	@ManyToOne
	@JoinColumn(name = "internet_id")
	private Plano internet;	
	
	@ManyToOne
	@JoinColumn(name = "movel_id")
	private Plano movel;	
	
	private Double mensalidade;
	
	private String promocao;
	
	private String fidelidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "combos")	
	private List<ClienteCombo> cliente = new ArrayList<>();

	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "COMBO_CATEGORIACOMBO",
		joinColumns = @JoinColumn(name = "combo_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
			)
	private List<CategoriaCombo> categorias = new ArrayList<>();	
	
	public Combo() {		
	}	

	public Combo(Integer id, Plano internet, Plano movel, Double mensalidade, String promocao, String fidelidade) {
		super();
		this.id = id;
		this.internet = internet;
		this.movel = movel;
		this.mensalidade = mensalidade;
		this.promocao = promocao;
		this.fidelidade = fidelidade;
	}


	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Plano getInternet() {
		return internet;
	}

	public void setInternet(Plano internet) {
		this.internet = internet;
	}

	public Plano getMovel() {
		return movel;
	}

	public void setMovel(Plano movel) {
		this.movel = movel;
	}

	public Double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Double mensalidade) {
		this.mensalidade = mensalidade;
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

	public List<CategoriaCombo> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaCombo> categorias) {
		this.categorias = categorias;
	}		

	public List<ClienteCombo> getCliente() {
		return cliente;
	}

	public void setCliente(List<ClienteCombo> cliente) {
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
		Combo other = (Combo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
