package com.client.webClient.beanConfigurations;

import com.client.webClient.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public User user()
    {
        return new User();
    }
    @Bean
    public User otherUser()
    {
        return new User();
    }
}
