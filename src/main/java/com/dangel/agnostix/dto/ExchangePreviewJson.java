package com.dangel.agnostix.dto;

import com.dangel.agnostix.basic.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExchangePreviewJson {
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
