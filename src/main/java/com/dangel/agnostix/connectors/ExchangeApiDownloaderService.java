package com.dangel.agnostix.connectors;

import com.dangel.agnostix.basic.ExchangeRate;
import com.dangel.agnostix.dto.xchange.XchangeJson;
import com.dangel.agnostix.enums.ExchangeSources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeApiDownloaderService extends AbstractApiDownloader{

    private static Logger logger = LoggerFactory.getLogger(ExchangeApiDownloaderService.class);

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
            try {
                JsonMapper jsonMapper = new JsonMapper();
                XchangeJson mappedJson = jsonMapper.readValue(response.getBody(), XchangeJson.class);
                List<ExchangeRate> result = new ArrayList<>(mappedJson.getExchangeRates().size());
                mappedJson.getExchangeRates().forEach((key, value) ->
                        result.add(
                                //TODO: find a way to fix / properly implement the currency and such, it should work without it
                                new ExchangeRate(key,"?",1,key,value, ExchangeSources.EXCHANGE_API)
                        )
                );
                return result;
            }
            catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }

        }

        return new ArrayList<>();
    }

}
