package pl.rynski.teammanagement.exception.handler;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.rynski.teammanagement.exception.ResourceAlreadyExists;
import pl.rynski.teammanagement.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorMessage(exception));
	}
	
	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<CustomErrorMessage> handleResourceAlreadyExists(ResourceAlreadyExists exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomErrorMessage(exception));
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<CustomErrorMessage> handleConstraintViolationException(ConstraintViolationException exception) {
		List<CustomValidationError> validationErrors = exception.getConstraintViolations()
				.stream()
				.map(error -> {
		        	String errorField = error.getPropertyPath().toString();
		        	return new CustomValidationError(errorField.substring(errorField.lastIndexOf(".") + 1), error.getMessage());
		        })
				.toList();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorMessage(exception, validationErrors));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<CustomValidationError> validationErrors = exception.getBindingResult().getFieldErrors()
				.stream()
				.map(error -> {
		        	return new CustomValidationError(error.getField(), error.getDefaultMessage());
		        })
				.toList();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorMessage(exception, validationErrors));
	}
}
