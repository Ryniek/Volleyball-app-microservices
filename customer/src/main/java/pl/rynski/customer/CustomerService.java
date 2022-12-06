package pl.rynski.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse response = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
		
		if(response.isFraudster()) {
			throw new IllegalStateException("fraudster");
		}
	}

}
