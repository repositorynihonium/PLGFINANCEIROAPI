package br.com.plataformalancamento.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.service.LancamentoFinanceiroService;

@RestController
@RequestMapping("/lancamento-financeiro")
public class LancamentoFinanceiroController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoFinanceiroService lancamentoFinanceiroService;
	
	@GetMapping
	public ResponseEntity<List<LancamentoFinanceiroDomain>> recuperar() {
		List<LancamentoFinanceiroDomain> lancamentoFinanceiroDomainList = this.lancamentoFinanceiroService.recuperar(); 
		return ResponseEntity.ok().body(lancamentoFinanceiroDomainList);
	}
	
	@GetMapping("{codigo}")
	public ResponseEntity<LancamentoFinanceiroDomain> recuperar(@PathVariable Long codigo) {
		LancamentoFinanceiroDomain lancamentoFinanceiroDomain = this.lancamentoFinanceiroService.recuperar(codigo);
		return ResponseEntity.ok().body(lancamentoFinanceiroDomain);
	}
	
}
