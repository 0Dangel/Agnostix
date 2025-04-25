package com.dangel.agnostix.basic;

import com.dangel.agnostix.enums.ExchangeSources;

public class ExchangeRate {
    private String country;
    private String currency;
    private int amount;
    private String code;
    private double price;
    private ExchangeSources source;

    public ExchangeRate() {
        amount = 1;
        price = 1.0;
        source = ExchangeSources.DEFAULT_EXCHANGE_SOURCE;
    }

    public ExchangeRate(String country, String currency, int amount, String code, double price, ExchangeSources source) {
        this.country = country;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.price = price;
        this.source = source;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ExchangeSources getSource() {
        return source;
    }

    public void setSource(ExchangeSources source) {
        this.source = source;
    }
}
