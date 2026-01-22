package com.example.prodwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ProdwebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdwebappApplication.class, args);
	}

}