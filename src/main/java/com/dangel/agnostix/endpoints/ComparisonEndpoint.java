package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.basic.ExchangeCacheMapEntity;
import com.dangel.agnostix.enums.ExchangeSources;
import com.dangel.agnostix.services.LocalCacheService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ComparisonEndpoint {

    private final LocalCacheService localCacheService;

    public ComparisonEndpoint(LocalCacheService localCacheService) {
        this.localCacheService = localCacheService;
    }

    @GetMapping(path = "/supported_currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<SourcePriceRecord>> getSupportedCurrencies() {

        List<ExchangeSources> sourcesList = localCacheService.getPossibleSources();
        Map<String, List<SourcePriceRecord>> mapOfPossibleCurrencies = new HashMap<>();

        for (ExchangeSources source : sourcesList) {
            ExchangeCacheMapEntity cacheEntity = localCacheService.getExchange(source);
            cacheEntity.getExchangeRates().forEach(exchange -> {
                List<SourcePriceRecord> list = mapOfPossibleCurrencies.computeIfAbsent(exchange.getMapKey(), k -> new ArrayList<>());
                list.add(new SourcePriceRecord(source, exchange.getPrice()));
            });
        }

        return mapOfPossibleCurrencies;
    }

    @GetMapping(path = "/{source1}-{source2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Double> getDifference(@PathVariable(name = "source1") String source1, @PathVariable(name = "source2") String source2) {

        Map<String, List<SourcePriceRecord>> mapOfRecord = getSupportedCurrencies();
        Map<String, Double> mapOfDifference = new HashMap<>();

        ExchangeSources eSrc1 = ExchangeSources.fromCode(source1);
        ExchangeSources eSrc2 = ExchangeSources.fromCode(source2);

        if (eSrc1 == ExchangeSources.DEFAULT_EXCHANGE_SOURCE || eSrc2 == ExchangeSources.DEFAULT_EXCHANGE_SOURCE) {
            return new HashMap<>();
        }


        mapOfRecord.forEach((k, v) -> {
            if (v.size() > 1) {
                SourcePriceRecord src1 = null;
                SourcePriceRecord src2 = null;
                for (SourcePriceRecord sourcePriceRecord : v) {
                    if (sourcePriceRecord.source.equals(eSrc1)) {
                        src1 = sourcePriceRecord;
                    }
                    else if (sourcePriceRecord.source.equals(eSrc2)) {
                        src2 = sourcePriceRecord;
                    }
                }
                if (src1 != null && src2 != null) {
                    mapOfDifference.put(k, src1.price - src2.price);
                }
            }
        });

        return mapOfDifference;
    }

    public record SourcePriceRecord(ExchangeSources source, double price) {
    }

}
