package br.com.plataformalancamento.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFinanceiroFilter {
	
	private String identificador;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimentoInicial;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimentoFinal;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamentoInicial;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamentoFinal;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataUltimaAlteracao;
	
	public LancamentoFinanceiroFilter() { }

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public Date getDataPagamentoInicial() {
		return dataPagamentoInicial;
	}

	public void setDataPagamentoInicial(Date dataPagamentoInicial) {
		this.dataPagamentoInicial = dataPagamentoInicial;
	}

	public Date getDataPagamentoFinal() {
		return dataPagamentoFinal;
	}

	public void setDataPagamentoFinal(Date dataPagamentoFinal) {
		this.dataPagamentoFinal = dataPagamentoFinal;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
}
