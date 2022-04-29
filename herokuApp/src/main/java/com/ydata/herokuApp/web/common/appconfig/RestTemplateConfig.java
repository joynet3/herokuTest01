package com.ydata.herokuApp.web.common.appconfig;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${system.host.sp}")
    private String SYS_SITE_SP;

    @Bean
    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException {

        RestTemplate restTemplate = null;
        if ("live".equals(SYS_SITE_SP)) {

            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

            HttpClient client = HttpClientBuilder.create().setMaxConnTotal(150).setMaxConnPerRoute(50).build();

            factory.setHttpClient(client);
            factory.setConnectTimeout(600000);
            factory.setReadTimeout(600000);

            restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
            restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

        } else {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            } };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

            HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
            customRequestFactory.setHttpClient(httpClient);
            customRequestFactory.setConnectTimeout(600000);
            customRequestFactory.setReadTimeout(600000);

            restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(customRequestFactory));
            restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        }

        return restTemplate;

    }

}

class RequestResponseLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        URI uri = request.getURI();
        traceRequest(request, body);

        ClientHttpResponse response = execution.execute(request, body);

        traceResponse(response, uri);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        StringBuilder reqLog = new StringBuilder();
        reqLog.append("[REQUEST] ").append("Uri : ").append(request.getURI()).append(", Method : ")
                .append(request.getMethod()).append(", Request Body : ")
                .append(new String(body, StandardCharsets.UTF_8));
        logger.info(reqLog.toString());
    }

    private void traceResponse(ClientHttpResponse response, URI uri) throws IOException {
        StringBuilder resLog = new StringBuilder();
        resLog.append("[RESPONSE] ").append("Uri : ").append(uri).append(", Status code : ")
                .append(response.getStatusCode()).append(", Response Body : ")
                .append(StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        logger.info(resLog.toString());
    }

}
