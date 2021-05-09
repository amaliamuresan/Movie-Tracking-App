package com.client.webClient.services.user;

import com.client.webClient.beans.User;
import com.client.webClient.ssltemplate.SSLUsingRestTemplate;
import com.client.webClient.ssltemplate.SSLUsingRestTemplateConfigurator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class RegisterService {
    @Autowired
    private String serverURL;
    @Autowired
    private SSLUsingRestTemplateConfigurator sslUsingRestTemplateConfigurator;

    public Map<String, String> postToServer(String email, String password,String displayName) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        String processedURL=serverURL;
        processedURL+="/api/users/register";
        RestTemplate restT=new SSLUsingRestTemplate(sslUsingRestTemplateConfigurator);
        Map<String,String> params=new HashMap<>();
        params.put("email",email);
        params.put("password",password);
        params.put("display_name",displayName);
        String response;
        ObjectMapper om=new ObjectMapper();
        Map<String,String> map=new HashMap<>();
        response=restT.postForObject(processedURL,params,String.class);
        map=om.readValue(response,map.getClass());
        return map;
    }
}
