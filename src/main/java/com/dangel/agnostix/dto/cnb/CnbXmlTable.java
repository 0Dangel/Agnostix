package com.dangel.agnostix.dto.cnb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CnbXmlTable {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "radek")
    private List<CnbXmlExchangeRate> rows;

    @JacksonXmlProperty(localName = "typ")
    private String type;

    public List<CnbXmlExchangeRate> getRows() {
        return rows;
    }

    public void setRows(List<CnbXmlExchangeRate> rows) {
        this.rows = rows;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
