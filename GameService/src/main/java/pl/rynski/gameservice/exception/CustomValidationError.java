package pl.rynski.gameservice.exception;

public record CustomValidationError(
		String field,
		String errorMessage){
}
