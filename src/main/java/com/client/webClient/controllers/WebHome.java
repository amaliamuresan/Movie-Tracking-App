package com.client.webClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebHome {
    @ResponseBody
    @RequestMapping("/")
    public String hello()
    {
        return "Hello World!";
    }
}
