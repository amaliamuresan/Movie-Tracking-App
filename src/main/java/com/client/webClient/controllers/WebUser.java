package com.client.webClient.controllers;

import com.client.webClient.beans.User;
import com.client.webClient.services.user.LoginService;
import com.client.webClient.services.user.OtherUserService;
import com.client.webClient.services.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class WebUser {
    @Autowired
    private LoginService loginService;
    @Autowired
    private OtherUserService otherUserService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private User user;
    @Autowired
    private User otherUser;
    @RequestMapping("/login")
    public String login() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException
    {
        user=loginService.postToServer("user2@company.com","123456");
        return "hello_login";
    }
    @RequestMapping("/profile/{uid}")
    @ResponseBody
    public User profile(@PathVariable("uid") String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        otherUser=otherUserService.getfromServer(uid);
        return otherUser;
    }
    @RequestMapping("/register")
    @ResponseBody
    public String register() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String result;
        Map<String,String> map;
        map=registerService.postToServer("user2345@company.com","123456","Display me");
        if(map.containsKey("Error"))
        {
            result="Registration Error: "+map.get("Error");
        }
        else
        {
            result="Registration success!";
        }
        return result;
    }
}
