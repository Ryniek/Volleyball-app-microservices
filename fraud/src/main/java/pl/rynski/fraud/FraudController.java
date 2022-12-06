package pl.rynski.fraud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {
	
	@GetMapping("/{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
		boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
		log.info("fraud check id " + customerId);
		return new FraudCheckResponse(isFraudulentCustomer);
	}
}
