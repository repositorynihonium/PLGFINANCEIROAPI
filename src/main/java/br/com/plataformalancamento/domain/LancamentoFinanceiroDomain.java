package br.com.plataformalancamento.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.plataformalancamento.enumeration.TipoSituacaoLancamentoFinanceiroEnumeration;

@Entity
@Table(name = "TB_LANCAMENTO_FINANCEIRO")
public class LancamentoFinanceiroDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO", nullable = false)
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "ID_FAVORECIDO")
	@NotNull
	private PessoaDomain favorecido;
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA_LANCAMENTO_FINANCEIRO")
	@JsonProperty("categoriaLancamentoFinanceiro")
	private CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain;
	
	// FIXME -- Refatorar esse campo para lista. Um lançamento deve aceitar varios produtos ou servicos
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO_SERVICO")
	@JsonProperty("produtoServico")
	private ProdutoServicoDomain produtoServicoDomain;
	
	@ManyToOne
	@JoinColumn(name = "ID_RESPONSAVEL_PAGAMENTO")
	private PessoaDomain responsavelPagamento;
	
	@ManyToOne
	@JoinColumn(name = "ID_FONTE_PAGAMENTO")
	private PessoaDomain fontePagamento;

	@Column(name = "IDENTIFICADOR", length = 80, unique = true, nullable = true)
	private String identificador;
	
	@JsonFormat(pattern = "DD/MM/YYYY")
	@Column(name = "DATA_VENCIMENTO", nullable = true)
	private Date dataVencimento;
	
	@JsonFormat(pattern = "DD/MM/YYYY")
	@Column(name = "DATA_PAGAMENTO", nullable = true)
	private Date dataPagamento;

	@JsonFormat(pattern = "DD/MM/YYYY")
	@Column(name = "DATA_ULTIMA_ALTERACAO", nullable = true)
	private Date dataUltimaAlteracao;
	
	@Column(name = "VALOR_TOTAL", nullable = true)
	private BigDecimal valorTotal;
	
	@Column(name = "VALOR_PAGAMENTO", nullable = true)
	private BigDecimal valorPagamento;
	
	@Column(name = "VALOR_DESCONTO", nullable = true)
	private BigDecimal valorDesconto;
	
	@Column(name = "FORMA_PAGAMENTO", nullable = true)
	private BigDecimal formaPagamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_SITUACAO_LANCAMENTO", nullable = false)
	@JsonProperty("tipoSituacaoLancamento")
	private TipoSituacaoLancamentoFinanceiroEnumeration tipoSituacaoLancamentoFinanceiro;
	
	@Column(name = "OBSERVACAO", nullable = true)
	private String observacao;
	
	public LancamentoFinanceiroDomain() { }

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public PessoaDomain getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(PessoaDomain favorecido) {
		this.favorecido = favorecido;
	}

	public CategoriaLancamentoFinanceiroDomain getCategoriaLancamentoFinanceiroDomain() {
		return categoriaLancamentoFinanceiroDomain;
	}

	public void setCategoriaLancamentoFinanceiroDomain(
			CategoriaLancamentoFinanceiroDomain categoriaLancamentoFinanceiroDomain) {
		this.categoriaLancamentoFinanceiroDomain = categoriaLancamentoFinanceiroDomain;
	}

	public ProdutoServicoDomain getProdutoServicoDomain() {
		return produtoServicoDomain;
	}

	public void setProdutoServicoDomain(ProdutoServicoDomain produtoServicoDomain) {
		this.produtoServicoDomain = produtoServicoDomain;
	}

	public PessoaDomain getResponsavelPagamento() {
		return responsavelPagamento;
	}

	public void setResponsavelPagamento(PessoaDomain responsavelPagamento) {
		this.responsavelPagamento = responsavelPagamento;
	}

	public PessoaDomain getFontePagamento() {
		return fontePagamento;
	}

	public void setFontePagamento(PessoaDomain fontePagamento) {
		this.fontePagamento = fontePagamento;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(BigDecimal formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public TipoSituacaoLancamentoFinanceiroEnumeration getTipoSituacaoLancamentoFinanceiro() {
		return tipoSituacaoLancamentoFinanceiro;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setTipoSituacaoLancamentoFinanceiro(
			TipoSituacaoLancamentoFinanceiroEnumeration tipoSituacaoLancamentoFinanceiro) {
		this.tipoSituacaoLancamentoFinanceiro = tipoSituacaoLancamentoFinanceiro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LancamentoFinanceiroDomain other = (LancamentoFinanceiroDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
