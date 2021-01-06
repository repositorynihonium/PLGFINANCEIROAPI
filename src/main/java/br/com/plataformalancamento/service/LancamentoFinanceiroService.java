package br.com.plataformalancamento.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.repository.LancamentoFinanceiroRepository;

@Service
public class LancamentoFinanceiroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;
	
	public List<LancamentoFinanceiroDomain> recuperar() {
		return this.lancamentoFinanceiroRepository.findAll();
	}
	
	public LancamentoFinanceiroDomain recuperar(Long codigo) {
		Optional<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainOptional = lancamentoFinanceiroRepository.findById(codigo);
		return lancamentoFinanceiroDomainOptional.get();
	}
	
}