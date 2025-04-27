package com.dangel.agnostix.rest;


import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import com.dangel.agnostix.connectors.ExchangeApiDownloaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    CnbApiDownloaderService cnbservice;
    ExchangeApiDownloaderService exchangeservice;

    public HealthCheck(CnbApiDownloaderService cnbservice, ExchangeApiDownloaderService exchangeservice) {
        this.cnbservice = cnbservice;
        this.exchangeservice = exchangeservice;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "<html><head><title>Health Check</title></head><body>" +
                "<h1>WebService</h1>" +
                "State: OK" +
                "<h1>CNB</h1>" +
                "State: " + cnbservice.getState().toString() +
                "<h1> <a href='https://github.com/fawazahmed0/exchange-api?tab=readme-ov-file'>Exchange Api</a></h1>" +
                "State: " + exchangeservice.getState().toString() +
                "</body></html>";
    }
}
