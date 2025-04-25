package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.connectors.CnbService;
import com.dangel.agnostix.dto.CnbDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CnbEndpoint {

    private CnbService cnbService;

    public CnbEndpoint(CnbService cnbService) {
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
