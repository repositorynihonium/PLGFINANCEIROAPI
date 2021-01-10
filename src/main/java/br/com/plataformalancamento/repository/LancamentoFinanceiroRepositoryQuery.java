package br.com.plataformalancamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;

public interface LancamentoFinanceiroRepositoryQuery {

	public Page<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable);
	
}
