package io.javabrains.AQITracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AqiTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AqiTrackerApplication.class, args);
	}

}
