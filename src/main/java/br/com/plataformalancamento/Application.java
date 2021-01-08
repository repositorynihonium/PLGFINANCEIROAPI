package br.com.plataformalancamento;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.plataformalancamento.domain.CategoriaLancamentoFinanceiroDomain;
import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.domain.PessoaDomain;
import br.com.plataformalancamento.domain.ProdutoServicoDomain;
import br.com.plataformalancamento.enumeration.TipoPessoaEnumeration;
import br.com.plataformalancamento.enumeration.TipoSituacaoLancamentoFinanceiroEnumeration;
import br.com.plataformalancamento.repository.CategoriaLancamentoFinanceiroRepository;
import br.com.plataformalancamento.repository.PessoaRepository;
import br.com.plataformalancamento.repository.ProdutoServicoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

//	@Autowired
//	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CategoriaLancamentoFinanceiroRepository categoriaLancamentoFinanceiroRepository;
	
	@Autowired
	private ProdutoServicoRepository produtoServicoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PessoaDomain pessoaDomain01 = new PessoaDomain();
			pessoaDomain01.setTipoPessoaEnumeration(TipoPessoaEnumeration.PESSOA_JURIDICA);
			pessoaDomain01.setNomeProprio(null);
			pessoaDomain01.setNomeFantasia("Tim Telecomunicações");
			pessoaDomain01.setNomeFonetico(null);
			pessoaDomain01.setIsAtivo(true);
			pessoaDomain01.setObservacao(null);
			
		PessoaDomain pessoaDomain02 = new PessoaDomain();
			pessoaDomain02.setTipoPessoaEnumeration(TipoPessoaEnumeration.PESSOA_FISICA);
			pessoaDomain02.setNomeProprio("Jamille Batista Alves");
			pessoaDomain02.setNomeFantasia(null);
			pessoaDomain02.setNomeFonetico(null);
			pessoaDomain02.setIsAtivo(false);
			pessoaDomain02.setObservacao(null);
		
		PessoaDomain pessoaDomain03 = new PessoaDomain();
			pessoaDomain03.setTipoPessoaEnumeration(TipoPessoaEnumeration.PESSOA_JURIDICA);
			pessoaDomain03.setNomeProprio(null);
			pessoaDomain03.setNomeFantasia("Banco Santander S.A");
			pessoaDomain03.setNomeFonetico(null);
			pessoaDomain03.setIsAtivo(true);
			pessoaDomain03.setObservacao(null);	
		
		PessoaDomain pessoaDomain04 = new PessoaDomain();
			pessoaDomain04.setTipoPessoaEnumeration(TipoPessoaEnumeration.PESSOA_FISICA);
			pessoaDomain04.setNomeProprio("José Quintinn");
			pessoaDomain04.setNomeFantasia(null);
			pessoaDomain04.setNomeFonetico(null);
			pessoaDomain04.setIsAtivo(false);
			pessoaDomain04.setObservacao(null);
			
			pessoaRepository.saveAll(Arrays.asList(pessoaDomain01, pessoaDomain02, pessoaDomain03, pessoaDomain04));
			
		CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain01 = new CategoriaLancamentoFinanceiroDomain();
			categoriaLancamentoFinanceiroDomain01.setNome("Despesa Fixa");
			categoriaLancamentoFinanceiroDomain01.setSigla("DF");
			
		CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain02 = new CategoriaLancamentoFinanceiroDomain();
			categoriaLancamentoFinanceiroDomain02.setNome("Despesa Variável");
			categoriaLancamentoFinanceiroDomain02.setSigla("DV");
			
		CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain03 = new CategoriaLancamentoFinanceiroDomain();
			categoriaLancamentoFinanceiroDomain03.setNome("Receita Fixa");
			categoriaLancamentoFinanceiroDomain03.setSigla("RF");
			
		CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain04 = new CategoriaLancamentoFinanceiroDomain();
			categoriaLancamentoFinanceiroDomain04.setNome("Receita Variável");
			categoriaLancamentoFinanceiroDomain04.setSigla("RV");
			
			categoriaLancamentoFinanceiroRepository.saveAll(Arrays.asList(categoriaLancamentoFinanceiroDomain01, categoriaLancamentoFinanceiroDomain02, categoriaLancamentoFinanceiroDomain03, categoriaLancamentoFinanceiroDomain04));
			
		ProdutoServicoDomain produtoServicoDomain = new ProdutoServicoDomain();
			produtoServicoDomain.setNome("Fatura Tim Beta");
			
			produtoServicoRepository.save(produtoServicoDomain);
			
		LancamentoFinanceiroDomain lancamentoFinanceiroDomain01 = new LancamentoFinanceiroDomain();
			lancamentoFinanceiroDomain01.setFavorecido(pessoaDomain01);
			lancamentoFinanceiroDomain01.setCategoriaLancamentoFinanceiroDomain(categoriaLancamentoFinanceiroDomain01);
			lancamentoFinanceiroDomain01.setProdutoServicoDomain(produtoServicoDomain);
			lancamentoFinanceiroDomain01.setResponsavelPagamento(pessoaDomain02);
			lancamentoFinanceiroDomain01.setDataVencimento(new Date());
			lancamentoFinanceiroDomain01.setDataPagamento(null);
			lancamentoFinanceiroDomain01.setValorPagamento(new BigDecimal(60));
			lancamentoFinanceiroDomain01.setTipoSituacaoLancamentoFinanceiro(TipoSituacaoLancamentoFinanceiroEnumeration.AGUARDANDO_PAGAMENTO);
			lancamentoFinanceiroDomain01.setFontePagamento(pessoaDomain03);
			lancamentoFinanceiroDomain01.setObservacao(null);
			
		LancamentoFinanceiroDomain lancamentoFinanceiroDomain02 = new LancamentoFinanceiroDomain();
			lancamentoFinanceiroDomain02.setFavorecido(pessoaDomain01);
			lancamentoFinanceiroDomain02.setCategoriaLancamentoFinanceiroDomain(categoriaLancamentoFinanceiroDomain01);
			lancamentoFinanceiroDomain02.setProdutoServicoDomain(produtoServicoDomain);
			lancamentoFinanceiroDomain02.setResponsavelPagamento(pessoaDomain04);
			lancamentoFinanceiroDomain02.setDataVencimento(new Date());
			lancamentoFinanceiroDomain02.setDataPagamento(null);
			lancamentoFinanceiroDomain02.setValorPagamento(new BigDecimal(600));
			lancamentoFinanceiroDomain02.setTipoSituacaoLancamentoFinanceiro(TipoSituacaoLancamentoFinanceiroEnumeration.AGUARDANDO_PAGAMENTO);
			lancamentoFinanceiroDomain02.setFontePagamento(pessoaDomain03);
			lancamentoFinanceiroDomain02.setObservacao(null);
			
//			lancamentoFinanceiroRepository.saveAll(Arrays.asList(lancamentoFinanceiroDomain01, lancamentoFinanceiroDomain02));
	}

}
