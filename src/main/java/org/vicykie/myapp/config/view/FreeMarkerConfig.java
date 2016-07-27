package org.vicykie.myapp.config.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * Created by vicykie on 2016/6/29.
 */
//@Configuration
public class FreeMarkerConfig {
    private static Logger logger = LogManager.getLogger(FreeMarkerConfig.class);

    @Bean
    public ViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        logger.info("freemarker init...");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/templates/");
        configurer.setDefaultEncoding("utf-8");
//        configurer.setTemplateLoaderPath(FREEMARKER_TEMPLATE_LOADER_PATH);
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("locale", "zh_CN");
//        settings.setProperty("template_update_delay","100");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }
}
