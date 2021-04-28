package com.client.webClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.client.webClient.config","com.client.webClient.controllers"})
public class WebConfiguration implements WebMvcConfigurer {
    /*@Override
    public void addViewControllers(final ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
    }*/
    /*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable("mvc");
    }*/
    @Bean
    public ViewResolver viewResolver()
    {
        InternalResourceViewResolver bean=new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
