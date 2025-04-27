package com.dangel.agnostix.basic;

public class ExchangeRate {
    private String country;
    private String currency;
    private int amount;
    private String code;
    private double price;

    public ExchangeRate() {
        amount = 1;
        price = 1.0;
    }

    public ExchangeRate(String country, String currency, int amount, String code, double price) {
        this.country = country;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.price = price;
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
}
