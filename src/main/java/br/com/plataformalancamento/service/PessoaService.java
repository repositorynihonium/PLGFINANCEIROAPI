package br.com.plataformalancamento.service;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataformalancamento.domain.PessoaDomain;
import br.com.plataformalancamento.repository.PessoaRepository;

@Service
public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public PessoaDomain recuperar(Long codigo) {
		Optional<PessoaDomain> pessoaDomainOptional = pessoaRepository.findById(codigo);
		return pessoaDomainOptional.get();
	}
	
	@Transactional
	public boolean verificarExistencia(Long codigoPessoa) {
		return this.pessoaRepository.existsById(codigoPessoa);
	}
	
}
