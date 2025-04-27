package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.connectors.ExchangeApiDownloaderService;
import com.dangel.agnostix.dto.ExchangePreviewJson;
import com.dangel.agnostix.enums.ExchangeSources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XchangeEndpoint {

    private ExchangeApiDownloaderService xchangeService;

    public XchangeEndpoint(ExchangeApiDownloaderService xchangeService) {
        this.xchangeService = xchangeService;
    }

    @GetMapping("/xchange")
    public String getXchange() {
        return xchangeService.getToday();
    }

    @GetMapping("xchange_parsed")
    public ExchangePreviewJson getXchangeParsed() {
        ExchangePreviewJson xchangeDto = new ExchangePreviewJson(ExchangeSources.EXCHANGE_API.getCode());
        xchangeDto.setRates(xchangeService.getTodayExchanges());
        return xchangeDto;
    }
}
