package com.mk.twinodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TwinodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwinodemoApplication.class, args);
	}

}
