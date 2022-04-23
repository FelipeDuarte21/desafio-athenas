package br.com.felipeduarte.athenas.desafio.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.felipeduarte.athenas.desafio.dto.PessoaFormDTO;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 80)
	private String nome;
	
	@Column(name = "cpf", length = 11)
	private String cpf;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "sexo", columnDefinition="enum('M', 'F')")
	private Character sexo;
	
	@Column(name = "altura", precision = 2)
	private Double altura;
	
	@Column(name = "peso", precision = 2)
	private Double peso;
	
	public Pessoa() {
		
	}

	public Pessoa(String pNome, String pCpf, LocalDate pData, 
			Character pSexo, Double pAltura, Double pPeso) {
		this.nome = pNome;
		this.cpf = pCpf;
		this.dataNascimento = pData;
		this.sexo = pSexo;
		this.altura = pAltura;
		this.peso = pPeso;
	}
	
	public Pessoa(PessoaFormDTO pessoaDTO) {
		this.nome = pessoaDTO.getNome();
		this.cpf = pessoaDTO.getCpf();
		this.dataNascimento = pessoaDTO.getDataNascimento();
		this.sexo = pessoaDTO.getSexo();
		this.altura = pessoaDTO.getAltura();
		this.peso = pessoaDTO.getPeso();
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

	@Override
	public int hashCode() {
		return Objects.hash(altura, cpf, dataNascimento, id, nome, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(altura, other.altura) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(peso, other.peso)
				&& Objects.equals(sexo, other.sexo);
	}
	
	public Double calcularPesoIdeal() {
		
		if(this.sexo == 'M')
			return (72.7 * this.altura) - 58.0;
		
		if(this.sexo == 'F')
			return (62.1 * this.altura) - 44.7;
		
		throw new IllegalArgumentException("Erro! sexo informado diferente de 'M' ou 'F'");
		
	}

}
