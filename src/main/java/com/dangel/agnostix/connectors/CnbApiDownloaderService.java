package com.dangel.agnostix.connectors;

import com.dangel.agnostix.dto.cnb.CnbXml;
import com.dangel.agnostix.basic.ExchangeRate;
import com.dangel.agnostix.enums.ExchangeSources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service mainly because i want to be able to change the URLs from .settings file
 * It also does not make sense to instantiate these everywhere anew.
 */
@Service
public class CnbApiDownloaderService extends AbstractApiDownloader {

    @Value("${foreign.api.cnb.xml:https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml}")
    private String URL_XML;

    @Value("${foreign.api.cnb.csv:https://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.csv}")
    private String URL_CSV;


    public HttpStatusCode getState(){
       return getState(URL_CSV);
    }


    public String getToday(){
        return getToday(URL_CSV);
    }

    /**
     * Returns today values converted to {@link ExchangeRate} format
     * @return
     */
    public List<ExchangeRate> getTodayExchanges() {
        ResponseEntity<String> response = makeRequest(URL_XML);
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
