package com.vendas.gestaovendas.Exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TratamentosErrosHandlerExcecao extends ResponseEntityExceptionHandler {

	private final static String VALIDATION_NOT_BLANK = "NotBlank";
	private final static String VALIDATION_LENGTH = "Length";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Errors> errors = GerarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handlerEmptyResultDataAcessException(EmptyResultDataAccessException ex, WebRequest request) {
	   String msgUsuario = "Recurso não encontrado";
	   String msgDesenvolvedor = ex.toString();
	   List<Errors> erro = Arrays.asList(new Errors(msgUsuario, msgDesenvolvedor));
	   return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Errors> GerarListaDeErros(BindingResult bindingResult) {
		List<Errors> errosList = new ArrayList<>();

		bindingResult.getFieldErrors().forEach(FieldError -> {
			String msgUsuario = tratarMensagemErroParaUsuario(FieldError);
			String dev = FieldError.toString();
			errosList.add(new Errors(msgUsuario, dev));
		});

		return errosList;
	}

	private String tratarMensagemErroParaUsuario(FieldError fieldError) {
		if (fieldError.getCode().equals(VALIDATION_NOT_BLANK)) {
			return fieldError.getDefaultMessage().concat(" é obrigatorio");
		}

		if (fieldError.getCode().equals(VALIDATION_LENGTH)) {
			return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
					fieldError.getArguments()[2], fieldError.getArguments()[1]));
		}
		return fieldError.toString();
	}
}
