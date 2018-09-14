package com.hsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients({"com.hsh.feignclient"})
public class ProcumentApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProcumentApplication.class, args);
	}
}
