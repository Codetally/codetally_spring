package com.codetally.model;

public class ShieldCost {
    private String multiplier;
    private String currency_sign;
    private String currency_abbreviation;
    private String amount;


    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setCurrency_sign(String currency_sign) {
        this.currency_sign = currency_sign;
    }

    public String getCurrency_sign() {
        return currency_sign;
    }

    public void setCurrency_abbreviation(String currency_abbreviation) {
        this.currency_abbreviation = currency_abbreviation;
    }

    public String getCurrency_abbreviation() {
        return currency_abbreviation;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }
}
