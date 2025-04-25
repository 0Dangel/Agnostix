package com.dangel.agnostix.connectors;

import com.dangel.agnostix.dto.ExchangesDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CnbService {

    private static final String CNB_URL = "https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.txt";

    /**
     * Internally used function to make simple requests
     * @param url
     * @return
     */
    private ResponseEntity<String> makeRequest(String url) {
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
    }

    public HttpStatusCode getState() {
        return makeRequest(CNB_URL).getStatusCode();
    }

    /**
     * returns a string of exchange values in CSV by CNB
     * @return
     */
    public String getToday() {
        ResponseEntity<String> response = makeRequest(CNB_URL);

        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return response.getStatusCode().toString();
    }

    /**
     * Returns today values converted to {@link ExchangesDto} format
     * TODO: Function currently not working, needs proper implementation. Currently a Stub
     * @return
     */
    public List<ExchangesDto> getTodayExchanges() {
        ResponseEntity<String> response = makeRequest(CNB_URL);
        if(response.getStatusCode() == HttpStatus.OK) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
