package com.client.webClient.controllers;

import com.client.webClient.beans.HelloBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class WebHelloWorld {
    @Autowired
    private HelloBean helloBean;

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

    public HelloBean getHelloBean() {
        return helloBean;
    }

    public void setHelloBean(HelloBean helloBean) {
        this.helloBean = helloBean;
    }
}
