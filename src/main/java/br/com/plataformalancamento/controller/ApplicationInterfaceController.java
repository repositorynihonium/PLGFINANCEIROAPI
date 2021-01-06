package br.com.plataformalancamento.controller;

import java.util.List;

public interface ApplicationInterfaceController<T> {

	public T cadastrar(Object T);
	
	public List<T> recuperar();
	
	public T recuperar(Long codigo);
	
	public T recuperarParametro(String parametro);
	
	public void remover(Long codigo);
	
	public void atualizar(Long codigo, Object T);
	
}
