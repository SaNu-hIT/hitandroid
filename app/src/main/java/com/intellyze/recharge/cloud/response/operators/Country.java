
package com.intellyze.recharge.cloud.response.operators;


import com.google.gson.annotations.SerializedName;



public class Country {

    @SerializedName("isoName")
    private String mIsoName;
    @SerializedName("name")
    private String mName;

    public String getIsoName() {
        return mIsoName;
    }

    public void setIsoName(String isoName) {
        mIsoName = isoName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
