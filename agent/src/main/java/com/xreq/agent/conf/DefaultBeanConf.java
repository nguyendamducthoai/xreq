package com.xreq.agent.conf;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Locale;

@Configuration
public class DefaultBeanConf {

    @Bean
    WebClient defaultWebClient() {
        return WebClient.builder().build();
    }

    @Bean
    Faker faker() {
        return new Faker();
    }

    @Bean
    FakeValuesService fakeValuesService() {
        return new FakeValuesService(
                new Locale("en-GB"), new RandomService());
    }

    @Bean
    @Primary
    public TemplateEngine stringTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setCacheable(false);
        stringTemplateResolver.setTemplateMode(TemplateMode.TEXT);
        templateEngine.setTemplateResolver(stringTemplateResolver);
        return templateEngine;
    }

}
