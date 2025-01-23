package com.backendExam.TafBookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TafBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TafBookingServiceApplication.class, args);
	}

}
