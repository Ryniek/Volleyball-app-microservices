package pl.rynski.fraud;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
	
	private final FraudCheckHistoryRepository repository;
	
	public boolean isFraudulentCustomer(Integer customerId) {
		repository.save(FraudCheckHistory.builder()
				.customerId(customerId)
				.isFraudster(false)
				.createdAt(LocalDateTime.now())
				.build());
		return false;
	}
}
