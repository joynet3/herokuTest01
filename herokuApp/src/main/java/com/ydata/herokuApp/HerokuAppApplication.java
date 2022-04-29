package com.ydata.herokuApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HerokuAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerokuAppApplication.class, args);
	}

}
