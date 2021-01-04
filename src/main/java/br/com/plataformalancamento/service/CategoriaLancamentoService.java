package br.com.plataformalancamento.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformalancamento.domain.CategoriaLancamentoDomain;
import br.com.plataformalancamento.exception.ObjetoNaoEncontradoException;
import br.com.plataformalancamento.repository.CategoriaLancamentoRepository;

@Service
public class CategoriaLancamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoriaLancamentoRepository categoriaLancamentoRepository;
	
	public CategoriaLancamentoService() { }
	
	@Transactional
	public CategoriaLancamentoDomain cadastrarCategoriaLancamento(CategoriaLancamentoDomain categoriaLancamentoDomain) {
		return categoriaLancamentoRepository.save(categoriaLancamentoDomain);
	}
	
	@Transactional
	public List<CategoriaLancamentoDomain> recuperarCategoriaLancamento() {
		return categoriaLancamentoRepository.findAll();
	}
	
	@Transactional
	public CategoriaLancamentoDomain recuperarCategoriaLancamento(Long codigo) {
		Optional<CategoriaLancamentoDomain> categoriaLancamentoDomainOptional = categoriaLancamentoRepository.findById(codigo);
		return categoriaLancamentoDomainOptional.orElseThrow( () -> new ObjetoNaoEncontradoException("O objeto com o código de identificação " + codigo + " não pode ser encontrado!"));
	}

}
