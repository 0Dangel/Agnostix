package com.dangel.agnostix.basic;

import com.dangel.agnostix.enums.ExchangeSources;

import java.time.LocalDateTime;
import java.util.List;

public class ExchangeCacheMapEntity {
    private final List<ExchangeRate> exchangeRates;
    private final ExchangeSources source;
    private final LocalDateTime updated;

    public ExchangeCacheMapEntity(List<ExchangeRate> exchangeRates, ExchangeSources source, LocalDateTime updated) {
        this.exchangeRates = exchangeRates;
        this.source = source;
        this.updated = updated;
    }

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    public ExchangeSources getSource() {
        return source;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}
