package com.zeco.zecomedical.notification.service.thymeleafConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {


    //this is the equivalent of adding: spring.mvc.static-path-pattern=/static/**  your to property file
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/public", "classpath:/static/");
                //.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
    }*/

    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        resolver.setPrefix("mailTemplates/"); // Location of thymeleaf template
        resolver.setCacheable(false); // Turning of cache to facilitate template changes
        resolver.setSuffix(".html"); // Template file extension
        resolver.setTemplateMode(TemplateMode.HTML); // Template Type
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine() {

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());

        return engine;
    }
}