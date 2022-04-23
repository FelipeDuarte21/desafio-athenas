package br.com.felipeduarte.athenas.desafio.dto;

public class PesoIdeal {
	
	private String nome;
	private String idade;
	private Double peso;
	
	public PesoIdeal() {
		
	}
	
	public PesoIdeal(String nome, String idade, Double peso) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.peso = peso;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
