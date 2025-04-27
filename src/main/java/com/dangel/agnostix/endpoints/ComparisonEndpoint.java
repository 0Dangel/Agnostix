package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.basic.ExchangeCacheMapEntity;
import com.dangel.agnostix.enums.ExchangeSources;
import com.dangel.agnostix.services.LocalCacheService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ComparisonEndpoint {

    private LocalCacheService localCacheService;

    public ComparisonEndpoint(LocalCacheService localCacheService) {
        this.localCacheService = localCacheService;
    }

    @GetMapping(path = "/supported_currencies",  produces= MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<SourcePriceRecord>> getSupportedCurrencies() {

        List<ExchangeSources> sourcesList = localCacheService.getPossibleSources();
        Map<String, List<SourcePriceRecord>> mapOfPossibleCurrencies = new HashMap<>();

        for (ExchangeSources source : sourcesList) {
            ExchangeCacheMapEntity cacheEntity = localCacheService.getExchange(source);
            cacheEntity.getExchangeRates().forEach(exchange -> {
               List<SourcePriceRecord> list =  mapOfPossibleCurrencies.computeIfAbsent(exchange.getMapKey(), k -> new ArrayList<>());
               list.add(new SourcePriceRecord(source,exchange.getPrice()));
            });
        }

        return mapOfPossibleCurrencies;
    }

    public record SourcePriceRecord(ExchangeSources source, double price) {}

}
