package br.com.plataformalancamento.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataformalancamento.domain.CategoriaLancamentoDomain;
import br.com.plataformalancamento.event.RecursoCriadoEvent;
import br.com.plataformalancamento.service.CategoriaLancamentoService;

@RestController
@RequestMapping("/categoria-lancamento")
public class CategoriaLancamentoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoriaLancamentoService categoriaLancamentoService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public CategoriaLancamentoController() { }
	
	@PostMapping
	public ResponseEntity<CategoriaLancamentoDomain> cadastrarCategoriaLancamento(@Valid @RequestBody CategoriaLancamentoDomain categoriaLancamentoDomain, HttpServletResponse httpServletResponse) {
		CategoriaLancamentoDomain categoriaLancamentoDomainResultado = categoriaLancamentoService.cadastrarCategoriaLancamento(categoriaLancamentoDomain);
		applicationEventPublisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, categoriaLancamentoDomain.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaLancamentoDomainResultado);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaLancamentoDomain>> recuperarCategoriaLancamento() {
		List<CategoriaLancamentoDomain> categoriaLancamentoDomainList = categoriaLancamentoService.recuperarCategoriaLancamento();
		return !categoriaLancamentoDomainList.isEmpty() ? ResponseEntity.ok().body(categoriaLancamentoDomainList) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaLancamentoDomain> recuperarCategoriaLancamento(@PathVariable Long codigo) {
		CategoriaLancamentoDomain categoriaLancamentoDomain = categoriaLancamentoService.recuperarCategoriaLancamento(codigo);
		return categoriaLancamentoDomain != null ? ResponseEntity.ok().body(categoriaLancamentoDomain) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCategoriaLancamento(@PathVariable Long codigo) {
		categoriaLancamentoService.removerCategoriaLancamento(codigo);
	}

}
