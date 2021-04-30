package com.client.webClient.controllers;

import com.client.webClient.beans.Greeting;
import com.client.webClient.beans.HelloBean;
import com.client.webClient.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Controller
@RequestMapping("/hello")
public class WebHelloWorld {
    @Autowired
    private HelloBean helloBean;
    @Autowired
    private Greeting greeting;
    @Autowired
    private GreetingService greetingService;

    @RequestMapping("")
    public String hello(Model model)
    {
        return "hello";
    }

    @RequestMapping("/bean")
    public String helloBean(Model model)
    {
        model.addAttribute("helloBean",helloBean);
        helloBean.setHelloMessage("Hello BEAN!!!");
        return "hello_bean";
    }
    @RequestMapping("/greeting")
    public String greeting(Model model) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        greeting=greetingService.getFromServer();
        model.addAttribute("greeting",greeting);
        return "hello_greeting";
    }

    public HelloBean getHelloBean() {
        return helloBean;
    }

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    public GreetingService getGreetingService() {
        return greetingService;
    }

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
