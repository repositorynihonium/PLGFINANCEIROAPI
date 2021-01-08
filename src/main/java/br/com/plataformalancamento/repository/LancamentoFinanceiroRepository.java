package br.com.plataformalancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiroDomain, Long>, LancamentoFinanceiroRepositoryQuery { }
