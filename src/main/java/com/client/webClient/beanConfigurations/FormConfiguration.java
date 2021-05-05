package com.client.webClient.beanConfigurations;

import com.client.webClient.forms.FormStringHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormConfiguration {
    @Bean
    public FormStringHandler formStringHandler()
    {
        return new FormStringHandler();
    }
}
