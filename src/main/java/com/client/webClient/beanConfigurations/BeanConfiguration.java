package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public HelloBean helloBean()
    {
        return new HelloBean();
    }
}
