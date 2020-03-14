package com.gitlab.model;

import com.google.gson.annotations.SerializedName;


public class Pusher {

    @SerializedName("email")
    private String mEmail;
    @SerializedName("name")
    private String mName;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
