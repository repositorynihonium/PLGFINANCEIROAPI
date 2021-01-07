package br.com.plataformalancamento.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.domain.PessoaDomain;
import br.com.plataformalancamento.exception.PessoaInexistenteInatvaException;
import br.com.plataformalancamento.repository.LancamentoFinanceiroRepository;

@Service
public class LancamentoFinanceiroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	public List<LancamentoFinanceiroDomain> recuperar() {
		return this.lancamentoFinanceiroRepository.findAll();
	}
	
	public LancamentoFinanceiroDomain recuperar(Long codigo) {
		Optional<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainOptional = lancamentoFinanceiroRepository.findById(codigo);
		return getLancamentoFinanceiroDomain(lancamentoFinanceiroDomainOptional);
	}
	
	public LancamentoFinanceiroDomain cadastrar(LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		PessoaDomain pessoaDomainResultado = pessoaService.recuperar(lancamentoFinanceiroDomain.getFavorecido().getCodigo());
		if(pessoaDomainResultado == null || !pessoaDomainResultado.getIsAtivo()) {
			throw new PessoaInexistenteInatvaException();
		}
		return lancamentoFinanceiroRepository.save(lancamentoFinanceiroDomain);
	}
	
	// FIXME -- Melhorar essa implementacao
	public LancamentoFinanceiroDomain atualizar(Long codigo, LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		Boolean isPessoaResponsavelPagamentoExistente = pessoaService.verificarExistencia(lancamentoFinanceiroDomain.getResponsavelPagamento().getCodigo());
		LancamentoFinanceiroDomain lancamentoFinanceiroDomainRetorno = this.recuperar(codigo);
		if(isPessoaResponsavelPagamentoExistente == false) {
			throw new PessoaInexistenteInatvaException();
		}
		PessoaDomain pessoaResponsavelPagamento = pessoaService.recuperar(lancamentoFinanceiroDomain.getResponsavelPagamento().getCodigo());
		if(!pessoaResponsavelPagamento.getIsAtivo()) {
			throw new PessoaInexistenteInatvaException();
		}
		BeanUtils.copyProperties(lancamentoFinanceiroDomain, lancamentoFinanceiroDomainRetorno, "codigo");
		return lancamentoFinanceiroRepository.save(lancamentoFinanceiroDomainRetorno);
	}
	
	public void remover(Long codigo) {
		this.lancamentoFinanceiroRepository.delete(this.recuperar(codigo));
	}
	
	private LancamentoFinanceiroDomain getLancamentoFinanceiroDomain(Optional<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainOptional) {
		return lancamentoFinanceiroDomainOptional.get();
	}
	
}
