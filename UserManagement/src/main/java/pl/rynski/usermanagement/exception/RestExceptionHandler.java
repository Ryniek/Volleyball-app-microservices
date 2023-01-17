package pl.rynski.usermanagement.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorMessage(exception));
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
}
