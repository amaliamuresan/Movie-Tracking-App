package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.Greeting;
import com.client.webClient.beans.HelloBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class BeanConfiguration {
    @Value("${ourServer.url}")
    private String ourServerUrl;
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
    @Bean
    public String serverURL()
    {
        return new String(ourServerUrl);
    }

}
