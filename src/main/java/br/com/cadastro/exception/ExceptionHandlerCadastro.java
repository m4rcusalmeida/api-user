package br.com.cadastro.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerCadastro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2792517944215651514L;

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<RespostaPadrao> noSuchElementException(NoSuchElementException e) {
		RespostaPadrao err = new RespostaPadrao("Objeto não encontrado");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<RespostaPadrao> illegalArgumentException(IllegalArgumentException e) {
		RespostaPadrao err = new RespostaPadrao(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<RespostaPadrao> emptyResultDataAccessException(EmptyResultDataAccessException e) {
		RespostaPadrao err = new RespostaPadrao("ID não enconrado na base de dados");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<RespostaPadrao> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		RespostaPadrao err = new RespostaPadrao("URL fora dos padrões");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<RespostaPadrao> httpMessageNotReadableException(HttpMessageNotReadableException e) {
		RespostaPadrao err = new RespostaPadrao("O perfil informado não existe na base de dados");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<RespostaValidacao>> methodArgumentNotValidException(ConstraintViolationException e) {
		List<RespostaValidacao> errs = new ArrayList<RespostaValidacao>();
		e.getConstraintViolations().forEach(er -> {
			errs.add(new RespostaValidacao(er.getMessageTemplate(), er.getPropertyPath().toString()));
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errs);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<RespostaPadrao> dataIntegrityViolationException(DataIntegrityViolationException e) {
		RespostaPadrao rp = new RespostaPadrao("email ja existe na base de dados");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rp);
	}

}
