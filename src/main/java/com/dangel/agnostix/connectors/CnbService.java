package com.dangel.agnostix.connectors;

import com.dangel.agnostix.dto.CnbXml;
import com.dangel.agnostix.basic.ExchangeRate;
import com.dangel.agnostix.enums.ExchangeSources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CnbService extends AbstractApiDownloader {

    @Value("${foreign.api.cnb.xml}")
    private String CNB_URL_XML ;

    @Value("${foreign.api.cnb.csv}")
    private String CNB_URL_CSV ;


    public HttpStatusCode getState(){
       return getState(CNB_URL_CSV);
    }


    /**
     * returns a string of exchange values in CSV by CNB
     * @return
     */
    public String getToday() {
        ResponseEntity<String> response = makeRequest(CNB_URL_CSV);

        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return response.getStatusCode().toString();
    }

    /**
     * Returns today values converted to {@link ExchangeRate} format
     * @return
     */
    public List<ExchangeRate> getTodayExchanges() {
        ResponseEntity<String> response = makeRequest(CNB_URL_XML);
        if(response.getStatusCode() == HttpStatus.OK) {
            try {
                XmlMapper xmlMapper = new XmlMapper();
                CnbXml requestedXml = xmlMapper.readValue(response.getBody(), CnbXml.class);
                ArrayList<ExchangeRate> listOfExchanges = new ArrayList<>();
                requestedXml.getTable().getRows().forEach(exchange -> {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setCode(exchange.getCode());
                    exchangeRate.setAmount(exchange.getAmount());
                    exchangeRate.setCurrency(exchange.getCurrency());
                    exchangeRate.setSource(ExchangeSources.CNB);
                    exchangeRate.setPrice(Double.parseDouble(exchange.getRate().replace(',','.')));
                    listOfExchanges.add(exchangeRate);
                });
                return listOfExchanges;
            } catch (JsonProcessingException e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
