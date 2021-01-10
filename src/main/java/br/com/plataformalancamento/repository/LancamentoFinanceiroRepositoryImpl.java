package br.com.plataformalancamento.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;

public class LancamentoFinanceiroRepositoryImpl implements LancamentoFinanceiroRepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<LancamentoFinanceiroDomain> lancamentoFinanceiroCriteriaQuery = criteriaBuilder.createQuery(LancamentoFinanceiroDomain.class);
		Root<LancamentoFinanceiroDomain> lancamentoFinanceiroRoot = lancamentoFinanceiroCriteriaQuery.from(LancamentoFinanceiroDomain.class);
		Predicate[] predicateList = criarRestricoes(lancamentoFinanceiroFilter, criteriaBuilder, lancamentoFinanceiroRoot);
			lancamentoFinanceiroCriteriaQuery.where(predicateList);
		TypedQuery<LancamentoFinanceiroDomain> lancamentoFinanceiroTypedQuery = entityManager.createQuery(lancamentoFinanceiroCriteriaQuery);
		adicionarRestricoesPaginacao(lancamentoFinanceiroTypedQuery, pageable);
		return new PageImpl<>(lancamentoFinanceiroTypedQuery.getResultList(), pageable, recuperarTotalResultado(lancamentoFinanceiroFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, CriteriaBuilder criteriaBuilder, Root<LancamentoFinanceiroDomain> lancamentoFinanceiroRoot) {
		List<Predicate> predicateList = new ArrayList<>();
		if(lancamentoFinanceiroFilter.getIdentificador() != null) {
			predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(lancamentoFinanceiroRoot.get("identificador")), "%" + lancamentoFinanceiroFilter.getIdentificador().toLowerCase() + "%"));
		}
		if(lancamentoFinanceiroFilter.getDataPagamentoInicial() != null) {
			predicateList.add(criteriaBuilder.greaterThanOrEqualTo(lancamentoFinanceiroRoot.get("dataPagamentoInicial"), lancamentoFinanceiroFilter.getDataPagamentoInicial()));
		}
		if(lancamentoFinanceiroFilter.getDataPagamentoFinal() != null) {
			predicateList.add(criteriaBuilder.lessThanOrEqualTo(lancamentoFinanceiroRoot.get("dataPagamentoFinal"), lancamentoFinanceiroFilter.getDataPagamentoFinal()));
		}
		// TODO -- Corrigir Filtro por Data de Ultima Alteracao
		if(lancamentoFinanceiroFilter.getDataUltimaAlteracao() != null) {
			predicateList.add(criteriaBuilder.greaterThanOrEqualTo(lancamentoFinanceiroRoot.get("dataUltimaAlteracao"), lancamentoFinanceiroFilter.getDataUltimaAlteracao()));
		}
		return predicateList.toArray(new Predicate[predicateList.size()]);
	}
	
	private void adicionarRestricoesPaginacao(TypedQuery<LancamentoFinanceiroDomain> lancamentoFinanceiroTypedQuery, Pageable pageable) {
		Integer paginaAtual = pageable.getPageNumber();
		Integer totalRegistroPagina = pageable.getPageSize();
		Integer primeiroRegistroPagina = paginaAtual * totalRegistroPagina;
		lancamentoFinanceiroTypedQuery.setFirstResult(primeiroRegistroPagina);
		lancamentoFinanceiroTypedQuery.setMaxResults(totalRegistroPagina);
	}
	
	private Long recuperarTotalResultado(LancamentoFinanceiroFilter lancamentoFinanceiroFilter) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<LancamentoFinanceiroDomain> lancamentoFinanceiroRoot = criteriaQuery.from(LancamentoFinanceiroDomain.class);
		Predicate[] predicateList = criarRestricoes(lancamentoFinanceiroFilter, criteriaBuilder, lancamentoFinanceiroRoot);
		criteriaQuery.where(predicateList);
		criteriaQuery.select(criteriaBuilder.count(lancamentoFinanceiroRoot));
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

}
