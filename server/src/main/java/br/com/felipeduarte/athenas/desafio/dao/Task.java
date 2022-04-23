package br.com.felipeduarte.athenas.desafio.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipeduarte.athenas.desafio.entity.Pessoa;

@Repository
public interface Task extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findByCpf(String cpf);
	
}
