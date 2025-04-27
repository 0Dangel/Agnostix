package com.dangel.agnostix.basic;

public class ExchangeRate {
    private String country;
    private String currency;
    private int amount;
    private String code;
    private String referenceCurrency;
    private double price;

    public ExchangeRate() {
        this("?","?",1,"?",1);
    }

    public ExchangeRate(String country, String currency, int amount, String code, double price) {
       this(country,currency,amount,code,"CZK",price);
    }

    public ExchangeRate(String country, String currency, int amount, String code, String referenceCurrency, double price) {
        this.country = country.trim().toUpperCase();
        this.currency = currency.trim().toUpperCase();
        this.amount = amount;
        this.code = code.trim().toUpperCase();
        this.price = price;
        this.referenceCurrency = referenceCurrency.trim().toUpperCase();
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

    public String getReferenceCurrency() {
        return referenceCurrency;
    }

    public void setReferenceCurrency(String referenceCurrency) {
        this.referenceCurrency = referenceCurrency;
    }
}
