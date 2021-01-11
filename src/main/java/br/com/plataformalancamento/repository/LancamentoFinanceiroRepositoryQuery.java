package br.com.plataformalancamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;
import br.com.plataformalancamento.projection.LancamentoFinanceiroProjection;

public interface LancamentoFinanceiroRepositoryQuery {

	public Page<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable);
	
	public Page<LancamentoFinanceiroProjection> filtrarLancamentoFinanceiroProjection(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable);
	
}
