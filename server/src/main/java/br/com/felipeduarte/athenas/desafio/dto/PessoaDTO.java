package br.com.felipeduarte.athenas.desafio.dto;

import java.time.LocalDate;

import br.com.felipeduarte.athenas.desafio.entity.Pessoa;

public class PessoaDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private Character sexo;
	private Double altura;
	private Double peso;
	
	public PessoaDTO() {
		
	}

	public PessoaDTO(Long id, String nome, String cpf, LocalDate dataNascimento, Character sexo, 
			Double altura, Double peso) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
	}
	
	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.sexo = pessoa.getSexo();
		this.altura = pessoa.getAltura();
		this.peso = pessoa.getPeso();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
