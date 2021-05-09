package com.client.webClient.controllers;

import com.client.webClient.beans.User;
import com.client.webClient.services.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Controller
@RequestMapping("/user")
public class WebUser {
    @Autowired
    private LoginService loginService;
    @Autowired
    private User user;
    @RequestMapping("/login")
    public String login() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException
    {
        user=loginService.postToServer("user2@company.com","123456");
        return "hello_login";
    }
}
