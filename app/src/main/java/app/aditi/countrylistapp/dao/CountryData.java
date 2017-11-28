package app.aditi.countrylistapp.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Aditi on 25-11-2017.
 */

public class CountryData implements Serializable {

    public static final String COUNTRY_DATA= "countryData";
    private String name;
    private String capital;
    private String region;
    private String subregion;
    private String native_name;
    private Double area;
    private String[] timezones;
    private long numericCode;
    private String flag;
    private String[] borders;
    private Double population;
    ArrayList<CurrenciesData> currencies;
    ArrayList<LanguageData> languages;

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public ArrayList<LanguageData> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<LanguageData> languages) {
        this.languages = languages;
    }

    public ArrayList<CurrenciesData> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<CurrenciesData> currencies) {
        this.currencies = currencies;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public long getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(long numericCode) {
        this.numericCode = numericCode;
    }



    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }



    public String getNative_name() {
        return native_name;
    }

    public void setNative_name(String native_name) {
        this.native_name = native_name;
    }



    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
