package br.com.plataformalancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformalancamento.domain.CategoriaLancamentoDomain;

public interface CategoriaLancamentoRepository extends JpaRepository<CategoriaLancamentoDomain, Long> { }
