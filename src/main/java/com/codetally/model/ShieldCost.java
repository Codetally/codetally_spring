package com.codetally.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greg on 04/09/17.
 */
public class ShieldCost {

    @SerializedName("currency_sign")
    private String currency_sign;

    @SerializedName("amount")
    private String amount;

    @SerializedName("multiplier")
    private String multiplier;

    @SerializedName("currency_abbreviation")
    private String currency_abbreviation;

    public String getCurrency_sign() {
        return currency_sign;
    }

    public void setCurrency_sign(String currency_sign) {
        this.currency_sign = currency_sign;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getCurrency_abbreviation() {
        return currency_abbreviation;
    }

    public void setCurrency_abbreviation(String currency_abbreviation) {
        this.currency_abbreviation = currency_abbreviation;
    }
}
