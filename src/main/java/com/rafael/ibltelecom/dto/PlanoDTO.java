package com.rafael.ibltelecom.dto;

import java.io.Serializable;

import com.rafael.ibltelecom.domain.Plano;



public class PlanoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private String download;

	private String upload;

	private Double mensalidade;

	private Double instalacao;

	private String promocao;

	private String fidelidade;

	private String detalhes;
	
	public PlanoDTO() {		
	}
	
	public PlanoDTO(Plano obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.download = obj.getDownload();
		this.upload = obj.getUpload();
		this.mensalidade = obj.getMensalidade();
		this.instalacao = obj.getInstalacao();
		this.promocao = obj.getPromocao();
		this.fidelidade = obj.getFidelidade();
		this.detalhes = obj.getDetalhes();
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

}
