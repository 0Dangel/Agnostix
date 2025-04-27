package com.dangel.agnostix.connectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class ExchangeApiDownloaderService extends AbstractApiDownloader{

    @Value("${foreign.api.xchange.json:https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/czk.json}")
    private String URL_JSON ;

    public HttpStatusCode getState(){
        return super.getState(URL_JSON);
    }


}
