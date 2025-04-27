package com.dangel.agnostix.dto;

import com.dangel.agnostix.basic.ExchangeCacheMapEntity;
import com.dangel.agnostix.basic.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ExchangePreviewJson implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("rates")
    private List<ExchangeRate> rates;
    @JsonProperty("origin")
    private String placeOfOrigin;

    /**
     * Do not remove, prepared for possible future de-serialization
     */
    public ExchangePreviewJson() {
    }

    public ExchangePreviewJson(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public ExchangePreviewJson(List<ExchangeRate> rates, String placeOfOrigin) {
        this.rates = rates;
        this.placeOfOrigin = placeOfOrigin;
    }

    /**
     * Factory method from ExchangeCacheMapEntity
     *
     * @param exchangeCacheMapEntity
     */
    public ExchangePreviewJson(ExchangeCacheMapEntity exchangeCacheMapEntity) {
        if (exchangeCacheMapEntity != null) {
            this.rates = exchangeCacheMapEntity.getExchangeRates();
            this.placeOfOrigin = exchangeCacheMapEntity.getSource().getCode();
        }
    }

    public List<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates = rates;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }
}
