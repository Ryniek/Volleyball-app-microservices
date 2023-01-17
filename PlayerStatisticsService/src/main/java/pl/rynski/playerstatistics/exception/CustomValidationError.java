package pl.rynski.playerstatistics.exception;

public record CustomValidationError(
		String field,
		String errorMessage){
}
