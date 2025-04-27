package com.dangel.agnostix.rest;


import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import com.dangel.agnostix.connectors.ExchangeApiDownloaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    CnbApiDownloaderService cnbservice ;
    ExchangeApiDownloaderService exchangeservice ;

    public HealthCheck(CnbApiDownloaderService cnbservice, ExchangeApiDownloaderService exchangeservice) {
        this.cnbservice = cnbservice;
        this.exchangeservice = exchangeservice;
    }

    @GetMapping("/health")
    public String healthCheck() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Health Check</title></head><body>");
        sb.append("<h1>WebService</h1>");
        sb.append("State: OK");
        sb.append("<h1>CNB</h1>");
        sb.append("State: " ).append(cnbservice.getState().toString());
        sb.append("<h1> <a href='https://github.com/fawazahmed0/exchange-api?tab=readme-ov-file'>Exchange Api</a></h1>");
        sb.append("State: ").append(exchangeservice.getState().toString());
        sb.append("</body></html>");

        return sb.toString();
    }
}
