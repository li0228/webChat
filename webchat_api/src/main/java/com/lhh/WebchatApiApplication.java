package com.lhh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhh.mapper")
public class WebchatApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebchatApiApplication.class, args);
	}

}
