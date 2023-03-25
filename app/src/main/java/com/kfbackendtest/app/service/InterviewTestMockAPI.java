package com.kfbackendtest.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kfbackendtest.app.model.Outage;
import com.kfbackendtest.app.model.SiteInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InterviewTestMockAPI {

    private final String BASE_URL = "https://api.krakenflex.systems/interview-tests-mock-api/v1";

    RestTemplate restTemplate = new RestTemplate();

    @Value("${apiKey}")
    private String apiKey;

    @Retryable
    public List<Outage> getOutages() {

        ResponseEntity<List<Outage>> responseEntity =
                restTemplate.exchange(
                        BASE_URL + "/outages",
                        HttpMethod.GET,
                        getEntity(),
                        new ParameterizedTypeReference<List<Outage>>() {
                        }
                );
        return responseEntity.getBody();
    }

    @Retryable
    public SiteInfo getSiteInfo(String siteId) {

        ResponseEntity<SiteInfo> responseEntity =
                restTemplate.exchange(
                        BASE_URL + "/site-info/" + siteId,
                        HttpMethod.GET,
                        getEntity(),
                        SiteInfo.class
                );
        return responseEntity.getBody();
    }

    @Retryable
    public void postSiteOutage(String siteId, List<Outage> outages) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(outages);
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            restTemplate.postForObject(
                    BASE_URL + "/site-outages/" + siteId,
                    request,
                    String.class
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        return entity;
    }
}
