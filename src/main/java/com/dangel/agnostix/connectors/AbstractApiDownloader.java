package com.dangel.agnostix.connectors;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractApiDownloader {


    /**
     * Internally used function to make simple requests
     *
     * @param url
     * @return
     */
    protected ResponseEntity<String> makeRequest(String url) {
        if (url == null || url.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    protected HttpStatusCode getState(String url) {
        if (url == null || url.isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        return makeRequest(url).getStatusCode();
    }
}
