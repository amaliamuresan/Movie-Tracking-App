package com.client.webClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class WebHome {
    /*@ResponseBody
    @RequestMapping("/")
    public String hello()
    {
        return "Hello World!";
    }*/
    @RequestMapping("/")
    public String index(Model model)
    {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model)
    {
        return "hello";
    }
}
