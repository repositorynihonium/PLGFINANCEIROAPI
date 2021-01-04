package br.com.plataformalancamento.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErroException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private static MessageSource messageSource;
	
	private String mensagem;
	private String causaErro;
	
	public ErroException(String mensagemTratada, String mensagemCausaErro) {
		this.mensagem = mensagemTratada;
		this.causaErro = mensagemCausaErro;
	}
	
	public static List<ErroException> criarListagemErros(BindingResult bindingResult) {
		List<ErroException> erroExceptionList = new ArrayList<>();
		for(FieldError filError : bindingResult.getFieldErrors()) {
			String mensagemException = messageSource.getMessage(filError, LocaleContextHolder.getLocale());
			String mensagemCausaErro = filError.toString();
			erroExceptionList.add(new ErroException(mensagemException, mensagemCausaErro));
		}
		
		return erroExceptionList;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCausaErro() {
		return causaErro;
	}

	public void setCausaErro(String causaErro) {
		this.causaErro = causaErro;
	}

}
