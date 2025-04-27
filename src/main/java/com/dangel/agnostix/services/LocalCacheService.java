package com.dangel.agnostix.services;

import com.dangel.agnostix.basic.ExchangeCacheMapEntity;
import com.dangel.agnostix.connectors.AbstractApiDownloader;
import com.dangel.agnostix.connectors.CnbApiDownloaderService;
import com.dangel.agnostix.connectors.ExchangeApiDownloaderService;
import com.dangel.agnostix.enums.ExchangeSources;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LocalCacheService {

    private final Map<ExchangeSources, AbstractApiDownloader> apiDownloadersMap = new EnumMap<>(ExchangeSources.class);

    private final Map<ExchangeSources, ExchangeCacheMapEntity> cacheMap = new EnumMap<>(ExchangeSources.class);
    private LocalDateTime dateOfUpdate = LocalDateTime.now();

    public LocalCacheService(ExchangeApiDownloaderService exchangeApiDownloaderService, CnbApiDownloaderService cnbApiDownloaderService) {
        apiDownloadersMap.put(ExchangeSources.CNB, cnbApiDownloaderService);
        apiDownloadersMap.put(ExchangeSources.EXCHANGE_API, exchangeApiDownloaderService);
    }

    public void updateAll() {
        for (ExchangeSources source : apiDownloadersMap.keySet()) {
            getExchange(source);
        }
    }

    public ExchangeCacheMapEntity getExchange(String source) {
        return getExchange(ExchangeSources.fromCode(source.toLowerCase()));
    }

    public ExchangeCacheMapEntity getExchange(ExchangeSources source) {
        AbstractApiDownloader downloader = apiDownloadersMap.get(source);
        if (downloader == null) {
            return null;
        }
        ExchangeCacheMapEntity result = cacheMap.computeIfAbsent(source, v -> new ExchangeCacheMapEntity(downloader.getTodayExchanges(), source, LocalDateTime.now()));

        if (!Objects.equals(result.getUpdated().toLocalDate(), LocalDate.now())) {
            cacheMap.put(source, new ExchangeCacheMapEntity(apiDownloadersMap.get(source).getTodayExchanges(), source, LocalDateTime.now()));
        }

        return result;
    }

    public List<ExchangeSources> getPossibleSources() {
        return apiDownloadersMap.keySet().stream().toList();
    }

    public Map<ExchangeSources, ExchangeCacheMapEntity> getExchangeMap() {
        return cacheMap;
    }

    public LocalDateTime getDateOfUpdate() {
        return dateOfUpdate;
    }

    public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
        this.dateOfUpdate = dateOfUpdate;
    }
}
