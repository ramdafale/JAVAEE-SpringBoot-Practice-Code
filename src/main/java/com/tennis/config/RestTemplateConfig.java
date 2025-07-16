package com.tennis.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

/**
 * Configuration for RestTemplate to handle HTTP requests with SSL certificate handling
 */
@Configuration
public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate() throws Exception {
        // Create trust strategy that trusts all certificates
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        // Create SSL context with trust all strategy
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        // Create SSL connection socket factory
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(
                sslContext, 
                new String[] { "TLSv1.2" }, 
                null, 
                NoopHostnameVerifier.INSTANCE);

        // Create connection manager with pooling
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        // Create request config with timeouts
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000) // 10 seconds
                .setSocketTimeout(30000) // 30 seconds
                .build();

        // Create HTTP client with SSL configuration and timeouts
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // Create request factory with custom HTTP client
        HttpComponentsClientHttpRequestFactory requestFactory = 
                new HttpComponentsClientHttpRequestFactory(httpClient);
        
        return new RestTemplate(requestFactory);
    }
}