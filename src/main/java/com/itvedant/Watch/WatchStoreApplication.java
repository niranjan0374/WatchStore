package com.itvedant.Watch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class WatchStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchStoreApplication.class, args);
	}

}
