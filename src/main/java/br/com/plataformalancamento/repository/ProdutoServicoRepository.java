package br.com.plataformalancamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.plataformalancamento.domain.ProdutoServicoDomain;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServicoDomain, Long> { }
