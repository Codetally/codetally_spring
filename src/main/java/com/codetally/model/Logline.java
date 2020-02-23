package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Logline {

    @SerializedName("level")
    private String mLevel;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("timestamp")
    private String mTimestamp;

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

}
