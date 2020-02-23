package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Timelog {

    @SerializedName("hours")
    private String mHours;
    @SerializedName("minutes")
    private String mMinutes;
    @SerializedName("seconds")
    private String mSeconds;

    public String getHours() {
        return mHours;
    }

    public void setHours(String hours) {
        mHours = hours;
    }

    public String getMinutes() {
        return mMinutes;
    }

    public void setMinutes(String minutes) {
        mMinutes = minutes;
    }

    public String getSeconds() {
        return mSeconds;
    }

    public void setSeconds(String seconds) {
        mSeconds = seconds;
    }

}
