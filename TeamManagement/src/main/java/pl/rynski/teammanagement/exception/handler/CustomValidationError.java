package pl.rynski.teammanagement.exception.handler;

public record CustomValidationError(
		String field,
		String errorMessage
		) {
}
