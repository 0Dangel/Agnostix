 package com.dangel.agnostix.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CnbXmlExchangeRate {

    @JacksonXmlProperty(isAttribute = true, localName = "kod")
    private String code;

    @JacksonXmlProperty(isAttribute = true, localName = "mena")
    private String currency;

    @JacksonXmlProperty(isAttribute = true, localName = "zeme")
    private String country;

    @JacksonXmlProperty(isAttribute = true, localName = "mnozstvi")
    private int amount;

    @JacksonXmlProperty(isAttribute = true, localName = "kurz")
    private String rate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
