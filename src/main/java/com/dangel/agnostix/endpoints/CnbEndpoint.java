package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import com.dangel.agnostix.dto.CnbDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CnbEndpoint {

    private CnbApiDownloaderService cnbService;

    public CnbEndpoint(CnbApiDownloaderService cnbService) {
        this.cnbService = cnbService;
    }

    @GetMapping("/cnb")
    public String getCnb() {
        return cnbService.getToday();
    }

    @GetMapping("cnb_parsed")
    public CnbDto getCnbParsed() {
        CnbDto cnbDto = new CnbDto();
        cnbDto.setRates(cnbService.getTodayExchanges());
        return cnbDto;
    }
}
