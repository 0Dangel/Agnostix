package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import com.dangel.agnostix.connectors.ExchangeApiDownloaderService;
import com.dangel.agnostix.dto.cnb.CnbDto;
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
    public CnbDto getXchangeParsed() {
        CnbDto cnbDto = new CnbDto();
        cnbDto.setRates(xchangeService.getTodayExchanges());
        return cnbDto;
    }
}
