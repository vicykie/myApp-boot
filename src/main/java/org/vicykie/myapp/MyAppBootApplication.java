package org.vicykie.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties
public class MyAppBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAppBootApplication.class, args);
    }
}
