package com.client.webClient.services.user;

import com.client.webClient.beans.User;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class OtherUserService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;
    @Autowired
    private User otherUser;

    public User getfromServer(String uid) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/"+uid;
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        User otherUser2;
        otherUser2=restT.getForObject(processedURL,User.class);
        BeanUtils.copyProperties(otherUser2,otherUser);
        return otherUser;
    }
}
