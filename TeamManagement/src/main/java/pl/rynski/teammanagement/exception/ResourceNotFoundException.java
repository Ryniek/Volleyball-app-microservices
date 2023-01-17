package pl.rynski.teammanagement.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException (String message) {
		super(message);
	}
}
