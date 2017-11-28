package app.aditi.countrylistapp.dao;

import java.io.Serializable;

/**
 * Created by deepi_000 on 28-11-2017.
 */

public class CurrenciesData implements Serializable {
    private String code;
    private String name;
    private String symbol;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
