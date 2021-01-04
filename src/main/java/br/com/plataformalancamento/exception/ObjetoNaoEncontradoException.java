package br.com.plataformalancamento.exception;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEncontradoException(String mensagemErro) {
		super(mensagemErro);
	}
	
	public ObjetoNaoEncontradoException(String mensagemErro, Throwable causaErro) {
		super(mensagemErro, causaErro);
	}

}
