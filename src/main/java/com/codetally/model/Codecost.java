package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Codecost {

    @SerializedName("charges")
    private List<Charge> mCharges;
    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("hourly_rates")
    private List<HourlyRate> mHourlyRates;

    public List<Charge> getCharges() {
        return mCharges;
    }

    public void setCharges(List<Charge> charges) {
        mCharges = charges;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public List<HourlyRate> getHourlyRates() {
        return mHourlyRates;
    }

    public void setHourlyRates(List<HourlyRate> hourlyRates) {
        mHourlyRates = hourlyRates;
    }

}
