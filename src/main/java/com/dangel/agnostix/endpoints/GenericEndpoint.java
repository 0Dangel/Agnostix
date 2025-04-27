package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.dto.ExchangePreviewJson;
import com.dangel.agnostix.services.LocalCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exchange")
@RestController
public class GenericEndpoint {

    private LocalCacheService localCacheService;

    public GenericEndpoint(LocalCacheService localCacheService) {
        this.localCacheService = localCacheService;
    }

    @GetMapping("/{api}")
    public ExchangePreviewJson getResult(@PathVariable("api") String api) {
        return new ExchangePreviewJson(localCacheService.getExchange(api));
    }
}
