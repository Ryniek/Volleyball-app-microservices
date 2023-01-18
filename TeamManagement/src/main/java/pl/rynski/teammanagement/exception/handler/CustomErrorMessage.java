package pl.rynski.teammanagement.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class CustomErrorMessage {
	
	private String errorMessage;
	private LocalDateTime timestamp;
	private List<CustomValidationError> validationErrors;
	
	public CustomErrorMessage(Throwable exception, List<CustomValidationError> validationErrors) {
		this.errorMessage = exception.getMessage();
		this.timestamp = LocalDateTime.now();
		this.validationErrors = validationErrors;
	}
	
	public CustomErrorMessage(Throwable exception) {
		this.errorMessage = exception.getMessage();
		this.timestamp = LocalDateTime.now();
	}
}
