package br.com.plataformalancamento.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataformalancamento.domain.LancamentoFinanceiroDomain;
import br.com.plataformalancamento.filter.LancamentoFinanceiroFilter;
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
	
	@PostMapping
	public ResponseEntity<LancamentoFinanceiroDomain> cadastrar(@Valid @RequestBody LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		LancamentoFinanceiroDomain lancamentoFinanceiroDomainRetorno = this.lancamentoFinanceiroService.cadastrar(lancamentoFinanceiroDomain);
		return ResponseEntity.created(null).body(lancamentoFinanceiroDomainRetorno);
	}
	
	@PutMapping("{codigo}")
	public ResponseEntity<LancamentoFinanceiroDomain> atualizar(@Valid @PathVariable Long codigo, @RequestBody LancamentoFinanceiroDomain lancamentoFinanceiroDomain) {
		LancamentoFinanceiroDomain lancamentoFinanceiroDomainResultado = this.lancamentoFinanceiroService.atualizar(codigo, lancamentoFinanceiroDomain);
		return ResponseEntity.ok().body(lancamentoFinanceiroDomainResultado);
	}
	
	@DeleteMapping("{codigo}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void remover(@Valid @PathVariable Long codigo) {
		this.lancamentoFinanceiroService.remover(codigo);
	}
	
	@GetMapping("/filtrar")
	public Page<LancamentoFinanceiroDomain> filtrarLancamentoFinanceiro(LancamentoFinanceiroFilter lancamentoFinanceiroFilter, Pageable pageable) {
		System.out.println(lancamentoFinanceiroFilter);
		System.out.println(pageable);
		return this.lancamentoFinanceiroService.filtrarLancamentoFinanceiro(lancamentoFinanceiroFilter, pageable);
	}
	
}
