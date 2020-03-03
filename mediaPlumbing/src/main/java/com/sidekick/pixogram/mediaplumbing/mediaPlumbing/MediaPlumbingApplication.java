package com.sidekick.pixogram.mediaplumbing.mediaPlumbing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MediaPlumbingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaPlumbingApplication.class, args);
	}

}
