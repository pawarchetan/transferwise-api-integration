package com.wevat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

    @Value("${transferWise.api.url}")
    private String apiUrl;

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity post(HttpEntity request, Class t) {
        return restTemplate.exchange(apiUrl, HttpMethod.POST, request, t);
    }
}
