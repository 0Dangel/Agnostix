package com.dangel.agnostix.dto.cnb;

import com.dangel.agnostix.basic.ExchangeRate;

import java.util.List;

public class CnbDto {
    private List<ExchangeRate> rates;

    public List<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates = rates;
    }
}
