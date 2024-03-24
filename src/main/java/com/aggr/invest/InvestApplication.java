package com.aggr.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestApplication.class, args);
	}

}
