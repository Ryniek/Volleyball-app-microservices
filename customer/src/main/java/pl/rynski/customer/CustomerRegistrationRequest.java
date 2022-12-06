package pl.rynski.customer;

public record CustomerRegistrationRequest(
		String firstName,
		String lastName,
		String email) {

}
