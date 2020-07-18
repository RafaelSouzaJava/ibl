package com.rafael.ibltelecom.domain.enums;



public enum Vencimento {

	CINCO(1, "05"),
	DEZ(2, "10");
	
	private int cod;
	private String descricao;
	
	private Vencimento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Vencimento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Vencimento x : Vencimento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
