package br.com.plataformalancamento.controller;

import java.io.Serializable;
import java.util.List;

public class ApplicationController<T> implements Serializable, ApplicationInterfaceController<T> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public T cadastrar(Object T) { return null; }

	@Override
	public List<T> recuperar() { return null; }

	@Override
	public T recuperar(Long codigo) { return null; }

	@Override
	public T recuperarParametro(String parametro) { return null; }

	@Override
	public void remover(Long codigo) { }

	@Override
	public void atualizar(Long codigo, Object T) { }
	
}
