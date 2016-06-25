package org.vicykie.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vicykie.myapp.bean.Test;

@RestController
@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties
public class MyAppBootApplication {
	@Autowired
	Test test;
	@RequestMapping("/")
	public String hello(){
		System.out.println(test.getName());
		return "ss/ss";
	}
	public static void main(String[] args) {
		SpringApplication.run(MyAppBootApplication.class, args);
	}
}
