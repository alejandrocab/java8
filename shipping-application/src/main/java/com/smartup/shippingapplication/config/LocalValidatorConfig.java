package com.smartup.shippingapplication.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class LocalValidatorConfig {
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
           LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
           bean.setValidationMessageSource(messageSource);
           return bean;
    }
}