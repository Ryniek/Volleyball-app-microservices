package pl.rynski.playerstatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.github.cloudyrock.spring.v5.EnableMongock;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableMongock
public class PlayerStatisticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerStatisticsServiceApplication.class, args);
	}

}
