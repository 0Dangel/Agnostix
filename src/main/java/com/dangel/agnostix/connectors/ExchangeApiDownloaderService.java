package com.dangel.agnostix.connectors;

import com.dangel.agnostix.basic.ExchangeRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeApiDownloaderService extends AbstractApiDownloader{

    @Value("${foreign.api.xchange.json:https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/czk.json}")
    private String URL_JSON ;

    public HttpStatusCode getState(){
        return super.getState(URL_JSON);
    }

    /**
     * returns a string of exchange values in CSV by Exchange-Api
     * @return
     */
    public String getToday() {
        return super.getToday(URL_JSON);
    }

    /**
     * Currently just a Stub - to be implemented
     */
    @Override
    public List<ExchangeRate> getTodayExchanges(){
        ResponseEntity<String> response = makeRequest(URL_JSON);
        if(response.getStatusCode() == HttpStatus.OK) {
            //Do some work

        }

        return new ArrayList<>();
    }

}
