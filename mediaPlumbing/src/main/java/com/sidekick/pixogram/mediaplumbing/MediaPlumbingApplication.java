package com.sidekick.pixogram.mediaplumbing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.sidekick.pixogram.mediaplumbing")
@EnableEurekaClient
@SpringBootApplication
public class MediaPlumbingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaPlumbingApplication.class, args);
	}

}
