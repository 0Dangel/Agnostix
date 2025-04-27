package com.dangel.agnostix.dto.cnb;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "kurzy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CnbXml {

    @JacksonXmlProperty(isAttribute = true, localName = "datum")
    private String date;

    @JacksonXmlProperty(isAttribute = true, localName = "banka")
    private String bank;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "tabulka")
    private CnbXmlTable table;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public CnbXmlTable getTable() {
        return table;
    }

    public void setTables(CnbXmlTable tables) {
        this.table = tables;
    }
}

