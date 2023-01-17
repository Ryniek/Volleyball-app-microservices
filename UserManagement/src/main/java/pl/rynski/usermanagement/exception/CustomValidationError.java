package pl.rynski.usermanagement.exception;

public record CustomValidationError(
		String field,
		String errorMessage
		) {
}
