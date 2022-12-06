package pl.rynski.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {
	
	@GetMapping
	public String getTest() {
		return "Test";
	}
	
	@PostMapping
	public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
		log.info("new registration {}", customerRegistrationRequest);
		customerService.registerCustomer(customerRegistrationRequest);
	}
}
