package br.com.plataformalancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformalancamento.domain.PessoaDomain;

public interface PessoaRepository extends JpaRepository<PessoaDomain, Long>{ }
