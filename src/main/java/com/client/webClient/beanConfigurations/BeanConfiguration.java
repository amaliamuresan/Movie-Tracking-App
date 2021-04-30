package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.Greeting;
import com.client.webClient.beans.HelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class BeanConfiguration {
    @Bean
    public HelloBean helloBean()
    {
        return new HelloBean();
    }
    @Bean
    public Greeting greeting()
    {
        return new Greeting();
    }

}
