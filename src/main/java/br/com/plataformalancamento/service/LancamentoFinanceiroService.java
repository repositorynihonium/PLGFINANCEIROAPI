package br.com.plataformalancamento.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.plataformalancamento.domain.CategoriaLancamentoFinanceiroDomain;
import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.domain.PessoaDomain;
import br.com.plataformalancamento.exception.PessoaInexistenteInatvaException;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;
import br.com.plataformalancamento.projection.LancamentoFinanceiroProjection;
import br.com.plataformalancamento.repository.CategoriaLancamentoFinanceiroRepository;
import br.com.plataformalancamento.repository.LancamentoFinanceiroRepository;
import br.com.plataformalancamento.utility.DateUtility;

@Service
public class LancamentoFinanceiroService implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Autowired
	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private CategoriaLancamentoFinanceiroRepository categoriaLancamentoFinanceiroRepository;

	public List<LancamentoFinanceiroDomain> recuperar() {
		return this.lancamentoFinanceiroRepository.findAll();
	}

	public LancamentoFinanceiroDomain recuperar(Long codigo) {
		Optional<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainOptional = lancamentoFinanceiroRepository.findById(codigo);
		return getLancamentoFinanceiroDomain(lancamentoFinanceiroDomainOptional);
	}

	public LancamentoFinanceiroDomain cadastrar(LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		PessoaDomain pessoaDomainResultado = pessoaService.recuperar(lancamentoFinanceiroDomain.getFavorecido().getCodigo());
		if (pessoaDomainResultado == null || !pessoaDomainResultado.getIsAtivo()) {
			throw new PessoaInexistenteInatvaException();
		}
		lancamentoFinanceiroDomain.setDataUltimaAlteracao(new Date());
		lancamentoFinanceiroDomain.setIdentificador(gerarIdentificadorLancamentoFinanceiro(lancamentoFinanceiroDomain.getCategoriaLancamentoFinanceiroDomain()));
		return lancamentoFinanceiroRepository.save(lancamentoFinanceiroDomain);
	}

	// FIXME -- Melhorar essa implementacao
	public LancamentoFinanceiroDomain atualizar(Long codigo, LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		Boolean isPessoaResponsavelPagamentoExistente = pessoaService
				.verificarExistencia(lancamentoFinanceiroDomain.getResponsavelPagamento().getCodigo());
		LancamentoFinanceiroDomain lancamentoFinanceiroDomainRetorno = this.recuperar(codigo);
		if (isPessoaResponsavelPagamentoExistente == false) {
			throw new PessoaInexistenteInatvaException();
		}
		PessoaDomain pessoaResponsavelPagamento = pessoaService
				.recuperar(lancamentoFinanceiroDomain.getResponsavelPagamento().getCodigo());
		if (!pessoaResponsavelPagamento.getIsAtivo()) {
			throw new PessoaInexistenteInatvaException();
		}
		BeanUtils.copyProperties(lancamentoFinanceiroDomain, lancamentoFinanceiroDomainRetorno, "codigo");
		return lancamentoFinanceiroRepository.save(lancamentoFinanceiroDomainRetorno);
	}	

	public void remover(Long codigo) {
		this.lancamentoFinanceiroRepository.delete(this.recuperar(codigo));
	}

	private LancamentoFinanceiroDomain getLancamentoFinanceiroDomain(
			Optional<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainOptional) {
		return lancamentoFinanceiroDomainOptional.get();
	}

	/**
	 * O identificador deve seguir o padrao:
	 * LANCAMENTO<DES/REC>-<DIA><MES><ANO><SEQUENCIAL>, Por exemplo:
	 * LANCAMENTOREC2021010701 FIXME -- Implementar Sequecial
	 */
	private String gerarIdentificadorLancamentoFinanceiro(CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain) {
		String textoPadrao = "LANCAMENTO";
		String receita = "REC";
		String despesa = "DES";
		Optional<CategoriaLancamentoFinanceiroDomain> categoriaLancamentoFinanceiroOptional = categoriaLancamentoFinanceiroRepository.findById(categoriaLancamentoFinanceiroDomain.getCodigo());
			categoriaLancamentoFinanceiroDomain = categoriaLancamentoFinanceiroOptional.get();
		StringBuilder stringBuilder = new StringBuilder(textoPadrao);
		if (categoriaLancamentoFinanceiroDomain.getSigla().equals("RV")
				|| categoriaLancamentoFinanceiroDomain.getSigla().equals("RF")) {
			stringBuilder.append(receita);
		}
		if (categoriaLancamentoFinanceiroDomain.getSigla().equals("DV")
				|| categoriaLancamentoFinanceiroDomain.getSigla().equals("DF")) {
			stringBuilder.append(despesa);
		}
		stringBuilder.append(DateUtility.recuperarDataAtual(DateUtility.FORMATO_YYYYMMDD));
		stringBuilder.append("00");
		stringBuilder.append(gerarSequencialIdentificador());
		System.out.println(stringBuilder.toString());
		return stringBuilder.toString();
	}
	
	// FIXME -- Ajustar tratar lancamentos financeiros retroativos
	private Integer gerarSequencialIdentificador() {
		// Se tiver uma determinado Lancamento cadastrado no mesmo dia deve-se incrementar o sequencial
		int contadorSequencialIdentificador = 1;
		for(LancamentoFinanceiroDomain lancamentoFinanceiroDomainReturn : this.recuperar()) {
			if(lancamentoFinanceiroDomainReturn.getDataUltimaAlteracao() == null) {
				lancamentoFinanceiroDomainReturn.setDataUltimaAlteracao(new Date());
			}
			if(DateUtility.formatarData(new Date(), DateUtility.FORMATO_YYYYMMDD).equals(DateUtility.formatarData(lancamentoFinanceiroDomainReturn.getDataUltimaAlteracao(), DateUtility.FORMATO_YYYYMMDD))) {
				contadorSequencialIdentificador += 1;
			}
		}
		return contadorSequencialIdentificador;
	}
	
	public Page<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable) {
		return lancamentoFinanceiroRepository.filtrarLancamentoFinanceiro(lancamentoFinanceiroFilter, pageable);
	}
	
	public Page<LancamentoFinanceiroProjection> filtrarLancamentoFinanceiroProjection(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable) {
		return lancamentoFinanceiroRepository.filtrarLancamentoFinanceiroProjection(lancamentoFinanceiroFilter, pageable);
	}

}
