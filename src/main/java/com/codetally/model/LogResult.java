package com.codetally.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LogResult {

    @SerializedName("loglines")
    private List<Logline> mLoglines;

    public List<Logline> getLoglines() {
        return mLoglines;
    }

    public void setLoglines(List<Logline> loglines) {
        mLoglines = loglines;
    }

}
