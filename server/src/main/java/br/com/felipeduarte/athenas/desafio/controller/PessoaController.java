package br.com.felipeduarte.athenas.desafio.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.felipeduarte.athenas.desafio.controller.exceptions.ObjectBadRequestException;
import br.com.felipeduarte.athenas.desafio.controller.exceptions.ObjectNotFoundException;
import br.com.felipeduarte.athenas.desafio.dto.PesoIdeal;
import br.com.felipeduarte.athenas.desafio.dto.PessoaDTO;
import br.com.felipeduarte.athenas.desafio.dto.PessoaFormDTO;
import br.com.felipeduarte.athenas.desafio.service.PessoaService;
import br.com.felipeduarte.athenas.desafio.service.exceptions.IllegalParameterException;
import br.com.felipeduarte.athenas.desafio.service.exceptions.ObjectNotFoundFromParameterException;

@CrossOrigin("*")
@RestController
@RequestMapping("/desafio-athenas/api/v1/pessoas")
public class PessoaController {

	private PessoaService pessoaService;

	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@PostMapping
	public ResponseEntity<PessoaDTO> incluir(@RequestBody @Valid PessoaFormDTO pessoaDTO, 
			UriComponentsBuilder uriBuilder){
		
		try {
			
			PessoaDTO pessoa = this.pessoaService.incluir(pessoaDTO);
			
			URI uri = uriBuilder.path("/desafio-athenas/api/v1/pessoas/{id}")
					.buildAndExpand(pessoa.getId()).toUri();
			
			return ResponseEntity.created(uri).body(pessoa);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
			
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> alterar(@PathVariable(name = "id") Long id, 
			@RequestBody @Valid PessoaFormDTO pessoaDTO){
		
		try {
			
			PessoaDTO pessoa = this.pessoaService.alterar(id, pessoaDTO);
			
			return ResponseEntity.ok(pessoa);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable(name = "id") Long id){
		
		this.pessoaService.excluir(id);
		
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> buscarPorId(@PathVariable(name = "id") Long id){
		
		try {
			
			PessoaDTO pessoa = this.pessoaService.buscarPorId(id);
			
			return ResponseEntity.ok(pessoa);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
	@GetMapping
	public ResponseEntity<Page<PessoaDTO>> pesquisar(
			@PageableDefault(page = 0, size = 10, direction = Direction.ASC, sort = "nome") Pageable paginacao){
		
		Page<PessoaDTO> pagPessoas = this.pessoaService.pesquisar(paginacao);
		
		return ResponseEntity.ok(pagPessoas);
		
	}
	
	@GetMapping("/peso-ideal/{id}")
	public ResponseEntity<PesoIdeal> buscarPesoIdeal(@PathVariable(name = "id") Long id){
		
		try {
			
			PesoIdeal peso = this.pessoaService.buscarPesoIdeal(id);
			
			return ResponseEntity.ok(peso);
			
		}catch(ObjectNotFoundFromParameterException ex) {
			throw new ObjectNotFoundException(ex.getMessage());
			
		}
		
	}
	
}
