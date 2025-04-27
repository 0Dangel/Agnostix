package com.dangel.agnostix.enums;

public enum ExchangeSources {
    CNB("cnb"),EXCHANGE_API("exchange_api"),CURRENCY_API("currency_api"), DEFAULT_EXCHANGE_SOURCE("default_exchange_source");

    private final String code;
    ExchangeSources(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public ExchangeSources fromCode(String code) {
        for (ExchangeSources source : ExchangeSources.values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return ExchangeSources.DEFAULT_EXCHANGE_SOURCE;
    }


}
