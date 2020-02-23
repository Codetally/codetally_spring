package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Charge {

    @SerializedName("action")
    private String mAction;
    @SerializedName("calculationtype")
    private String mCalculationtype;
    @SerializedName("chargeamount")
    private String mChargeamount;
    @SerializedName("chargeref")
    private String mChargeref;
    @SerializedName("chargetype")
    private String mChargetype;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("event")
    private String mEvent;

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        mAction = action;
    }

    public String getCalculationtype() {
        return mCalculationtype;
    }

    public void setCalculationtype(String calculationtype) {
        mCalculationtype = calculationtype;
    }

    public String getChargeamount() {
        return mChargeamount;
    }

    public void setChargeamount(String chargeamount) {
        mChargeamount = chargeamount;
    }

    public String getChargeref() {
        return mChargeref;
    }

    public void setChargeref(String chargeref) {
        mChargeref = chargeref;
    }

    public String getChargetype() {
        return mChargetype;
    }

    public void setChargetype(String chargetype) {
        mChargetype = chargetype;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

}
