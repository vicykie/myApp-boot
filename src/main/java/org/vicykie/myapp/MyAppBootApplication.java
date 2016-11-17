package org.vicykie.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties
@EnableAsync
public class MyAppBootApplication {

    /**
     * 自定义错误页面
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/static/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/static/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/static/500.html");
//            ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/static/500.html");
            container.addErrorPages(error401Page, error404Page,error500Page);
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(MyAppBootApplication.class, args);
    }
}
