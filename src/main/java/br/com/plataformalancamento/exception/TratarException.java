package br.com.plataformalancamento.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TratarException extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Captura erros, por exemplo, de requisicoes com campos nao mapeados
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemException = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemCausaErro = ex.getCause().getMessage();
		List<ErroException> erroExceptionList = Arrays.asList(new ErroException(mensagemException, mensagemCausaErro));
		return handleExceptionInternal(ex, erroExceptionList, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Captura erros, por exemplo, de validacao de campos nulos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
		List<ErroException> erroExceptionList = ErroException.criarListagemErros(methodArgumentNotValidException.getBindingResult(), messageSource);
		return handleExceptionInternal(methodArgumentNotValidException, erroExceptionList, httpHeaders, HttpStatus.BAD_REQUEST, webRequest);
	}
	
	/**
	 * Captura erros, por exemplo, da validacao de campos nao encontrados
	 * TODO Encaminhar mensagem tratada para o <b>frontent</b>
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleRuntimeException(RuntimeException runtimeException) { }
	
	/**
	 * Captura erros de negocio para pessoas inexistentes ou inativas
	 */
	@ExceptionHandler({ PessoaInexistenteInatvaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteInatvaException(PessoaInexistenteInatvaException pessoaInexistenteInatvaException) {
		String mensagemException = messageSource.getMessage("pessoa.inativa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemCausaErro = pessoaInexistenteInatvaException.getMessage();
		List<ErroException> erroExceptionList = Arrays.asList(new ErroException(mensagemException, mensagemCausaErro));
		return ResponseEntity.ok().body(erroExceptionList);
	}
	
}
