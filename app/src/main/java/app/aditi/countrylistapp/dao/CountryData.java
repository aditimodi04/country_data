package app.aditi.countrylistapp.dao;

import java.io.Serializable;

/**
 * Created by Aditi on 25-11-2017.
 */

public class CountryData implements Serializable {

    private String name;
    private String capital;
    private Integer[] calling_codes;
    private String region;
    private String sub_region;
    private String native_name;
    private long numeric_code;
    private String flag;

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

    public Integer[] getCalling_codes() {
        return calling_codes;
    }

    public void setCalling_codes(Integer[] calling_codes) {
        this.calling_codes = calling_codes;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSub_region() {
        return sub_region;
    }

    public void setSub_region(String sub_region) {
        this.sub_region = sub_region;
    }

    public String getNative_name() {
        return native_name;
    }

    public void setNative_name(String native_name) {
        this.native_name = native_name;
    }

    public long getNumeric_code() {
        return numeric_code;
    }

    public void setNumeric_code(long numeric_code) {
        this.numeric_code = numeric_code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
