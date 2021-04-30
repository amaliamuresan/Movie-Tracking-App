package com.client.webClient.ssltemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class SSLUsingRestTemplate extends RestTemplate {
    public SSLUsingRestTemplate(SSLUsingRestTemplateConfigurator sslConfig)
    {
        super(sslConfig.getFactory());
    }
}
