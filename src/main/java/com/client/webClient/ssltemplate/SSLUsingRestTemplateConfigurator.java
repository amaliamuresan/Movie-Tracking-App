package com.client.webClient.ssltemplate;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import sun.net.www.http.HttpClient;


import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class SSLUsingRestTemplateConfigurator {
    private HttpComponentsClientHttpRequestFactory factory;

    public SSLUsingRestTemplateConfigurator(Resource trustStore,String trustStorePassword) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslContext=new SSLContextBuilder().loadTrustMaterial(trustStore.getURL(),trustStorePassword.toCharArray()).build();
        SSLConnectionSocketFactory socketFactory=new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient= HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        factory=new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    public HttpComponentsClientHttpRequestFactory getFactory() {
        return factory;
    }

}
