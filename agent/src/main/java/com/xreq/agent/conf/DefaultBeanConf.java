package com.xreq.agent.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DefaultBeanConf {

    @Bean
    WebClient defaultWebClient() {
        return WebClient.builder().build();
    }
}
