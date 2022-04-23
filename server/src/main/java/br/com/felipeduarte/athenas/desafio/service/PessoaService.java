package br.com.felipeduarte.athenas.desafio.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipeduarte.athenas.desafio.dao.Task;
import br.com.felipeduarte.athenas.desafio.dto.PesoIdeal;
import br.com.felipeduarte.athenas.desafio.dto.PessoaDTO;
import br.com.felipeduarte.athenas.desafio.dto.PessoaFormDTO;
import br.com.felipeduarte.athenas.desafio.entity.Pessoa;
import br.com.felipeduarte.athenas.desafio.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.athenas.desafio.service.exceptions.ObjectNotFoundFromParameterException;

@Service
public class PessoaService {
	
	private Task task;
	
	@Autowired
	public PessoaService(Task dao) {
		this.task = dao;
	}
	
	public PessoaDTO incluir(PessoaFormDTO pessoaFormDTO) {
		
		Optional<Pessoa> optPessoa = this.task.findByCpf(pessoaFormDTO.getCpf());
		
		if(optPessoa.isPresent())
			throw new IllegalParameterException("Erro! pessoa já cadastrada!");
		
		Pessoa pessoa = new Pessoa(pessoaFormDTO);
		
		pessoa = this.task.save(pessoa);
		
		return new PessoaDTO(pessoa);
		
	}
	
	public PessoaDTO alterar(Long id, PessoaFormDTO pessoaFormDTO) {
		
		Optional<Pessoa> optPessoa = this.task.findById(id);
		
		if(!optPessoa.isPresent())
			throw new ObjectNotFoundFromParameterException("Erro! pessoa não encontrada!");
		
		Pessoa pessoa = optPessoa.get();
		pessoa.setNome(pessoaFormDTO.getNome());
		pessoa.setCpf(pessoaFormDTO.getCpf());
		pessoa.setDataNascimento(pessoaFormDTO.getDataNascimento());
		pessoa.setSexo(pessoaFormDTO.getSexo());
 		pessoa.setAltura(pessoaFormDTO.getAltura());
 		pessoa.setPeso(pessoaFormDTO.getPeso());
 		
 		pessoa = this.task.save(pessoa);
 		
 		return new PessoaDTO(pessoa);
		
	}
	
	public void excluir(Long id) {
		
		Optional<Pessoa> optPessoa = this.task.findById(id);
		
		if(optPessoa.isPresent())
			this.task.delete(optPessoa.get());
		
	}
	
	public PessoaDTO buscarPorId(Long id) {
		
		Optional<Pessoa> optPessoa = this.task.findById(id);
		
		if(!optPessoa.isPresent())
			throw new ObjectNotFoundFromParameterException("Erro! pessoa não encontrada para o id informado!");
		
		return new PessoaDTO(optPessoa.get());
		
	}
	
	public Page<PessoaDTO> pesquisar(Pageable paginacao){
		
		Page<Pessoa> pagPessoas = this.task.findAll(paginacao);
		
		return pagPessoas.map(PessoaDTO::new);
		
	}

	public PesoIdeal buscarPesoIdeal(Long id) {
		
		Optional<Pessoa> optPessoa = this.task.findById(id);
		
		if(!optPessoa.isPresent())
			throw new ObjectNotFoundFromParameterException("Erro! pessoa não encontrada para o id informado!");
		
		PesoIdeal p = new PesoIdeal();
		p.setNome(optPessoa.get().getNome());
		
		int idade = LocalDate.now().getYear() - optPessoa.get().getDataNascimento().getYear();
		p.setIdade(Integer.toString(idade));
		
		DecimalFormat f = new DecimalFormat("#.##");
		
		p.setPeso(Double.valueOf(f.format(optPessoa.get().calcularPesoIdeal()).replace(",", ".")));
		
		return p;
		
	}

}
