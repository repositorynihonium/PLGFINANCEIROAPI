package br.com.plataformalancamento.repository;

import java.util.List;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;

public interface LancamentoFinanceiroRepositoryQuery {

	public List<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter);
	
}
