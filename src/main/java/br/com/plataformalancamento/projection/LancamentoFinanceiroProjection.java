package br.com.plataformalancamento.projection;

import java.math.BigDecimal;
import java.util.Date;

public class LancamentoFinanceiroProjection {
	
	private Long codigo;
	
	private String favorecido;
	
	private String produtoServicoDomain;
	
	private Date dataPagamento;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;
	
	private BigDecimal valorPagamento;
	
	private String responsavelPagamento;
	
	private String fontePagamento;

	public LancamentoFinanceiroProjection(Long codigo, String favorecido, String produtoServicoDomain, Date dataPagamento, BigDecimal valorTotal, BigDecimal valorDesconto, BigDecimal valorPagamento, String responsavelPagamento, String fontePagamento) {
		this.codigo = codigo;
		this.favorecido = favorecido;
		this.produtoServicoDomain = produtoServicoDomain;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
		this.valorDesconto = valorDesconto;
		this.valorPagamento = valorPagamento;
		this.responsavelPagamento = responsavelPagamento;
		this.fontePagamento = fontePagamento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(String favorecido) {
		this.favorecido = favorecido;
	}

	public String getProdutoServicoDomain() {
		return produtoServicoDomain;
	}

	public void setProdutoServicoDomain(String produtoServicoDomain) {
		this.produtoServicoDomain = produtoServicoDomain;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public String getResponsavelPagamento() {
		return responsavelPagamento;
	}

	public void setResponsavelPagamento(String responsavelPagamento) {
		this.responsavelPagamento = responsavelPagamento;
	}

	public String getFontePagamento() {
		return fontePagamento;
	}

	public void setFontePagamento(String fontePagamento) {
		this.fontePagamento = fontePagamento;
	}
	
}
