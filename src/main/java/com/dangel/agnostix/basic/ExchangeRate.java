package com.dangel.agnostix.basic;

public class ExchangeRate {
    private String country;
    private String currency;
    private int amount;
    private String code;
    private String referenceCode;
    private double price;

    public ExchangeRate() {
        this("?","?",1,"?",1);
    }

    public ExchangeRate(String country, String currency, int amount, String code, double price) {
       this(country,currency,amount,code,"CZK",price);
    }

    public ExchangeRate(String country, String currency, int amount, String code, String referenceCode, double price) {
        this.country = country.trim().toUpperCase();
        this.currency = currency.trim().toUpperCase();
        this.amount = amount;
        this.code = code.trim().toUpperCase();
        this.price = price;
        this.referenceCode = referenceCode.trim().toUpperCase();
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
        this.currency = currency.trim().toUpperCase();
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

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode.trim().toUpperCase();
    }

    public String getMapKey(){
        return code+"_"+ referenceCode;
    }
}
