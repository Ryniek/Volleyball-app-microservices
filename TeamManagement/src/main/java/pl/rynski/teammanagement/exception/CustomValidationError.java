package pl.rynski.teammanagement.exception;

public record CustomValidationError(
		String field,
		String errorMessage
		) {
}
