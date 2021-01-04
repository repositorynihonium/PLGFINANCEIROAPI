package br.com.plataformalancamento.exception;

public class ErroIntegridadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErroIntegridadeException(String mensagemErro) {
		super(mensagemErro);
	}
	
	public ErroIntegridadeException(String mensagemErro, Throwable causaErro) {
		super(mensagemErro, causaErro);
	}

}
