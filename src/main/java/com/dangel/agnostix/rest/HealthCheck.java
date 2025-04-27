package com.dangel.agnostix.rest;


import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    CnbApiDownloaderService cnbservice ;

    public HealthCheck(CnbApiDownloaderService cnbservice) {
        this.cnbservice = cnbservice;
    }

    @GetMapping("/health")
    public String healthCheck() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Health Check</title></head><body>");
        sb.append("<h1>WebService</h1>");
        sb.append("State: OK");
        sb.append("<h1>CNB</h1>");
        sb.append("State: " ).append(cnbservice.getState().toString());
        sb.append("<h1>WebService</h1>");
        sb.append("State: OK");
        sb.append("</body></html>");

        return sb.toString();
    }
}
