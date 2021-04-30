package com.client.webClient.beanConfigurations;

import com.client.webClient.models.Greeting;
import com.client.webClient.beans.HelloBean;
import com.client.webClient.services.GreetingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("application.properties")
public class BeanConfiguration {
    @Bean
    public HelloBean helloBean()
    {
        return new HelloBean();
    }
    /*@Bean
    @Scope("prototype")
    public Greeting greeting()
    {
        Greeting greeting= GreetingService.getFromServer();
        return greeting;
    }*/

}
