package com.dangel.agnostix.connectors;

import com.dangel.agnostix.basic.ExchangeRate;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    /**
     * returns a string of exchange values in CSV by CNB
     *
     * @return
     */
    protected String getToday(String url) {
        if (url == null || url.isEmpty()) {
            return HttpStatus.BAD_REQUEST.getReasonPhrase();
        }

        ResponseEntity<String> response = makeRequest(url);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return response.getStatusCode().toString();
    }

    /**
     * This just so I can use this AbstractClass as an Interface as well
     *
     * @return
     */
    public abstract List<ExchangeRate> getTodayExchanges();
}
