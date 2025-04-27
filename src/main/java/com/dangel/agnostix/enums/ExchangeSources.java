package com.dangel.agnostix.enums;

public enum ExchangeSources {
    CNB("cnb"), EXCHANGE_API("xchange"), CURRENCY_API("currency_api"), DEFAULT_EXCHANGE_SOURCE("default_exchange_source");

    private final String code;

    ExchangeSources(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ExchangeSources fromCode(String code) {
        for (ExchangeSources source : ExchangeSources.values()) {
            if (source.getCode().equalsIgnoreCase(code)) {
                return source;
            }
        }
        return ExchangeSources.DEFAULT_EXCHANGE_SOURCE;
    }
}
