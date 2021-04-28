package com.client.webClient.config;

import com.client.webClient.config.WebConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext sct) throws ServletException
    {
        AnnotationConfigWebApplicationContext root=new AnnotationConfigWebApplicationContext();
        root.scan("com.client.webClient");
        /*root.register(WebConfiguration.class);
        root.refresh();
        root.setServletContext(sct);*/
        sct.addListener(new ContextLoaderListener(root));
        ServletRegistration.Dynamic appServlet=sct.addServlet("mvc",new DispatcherServlet(root));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }

}
