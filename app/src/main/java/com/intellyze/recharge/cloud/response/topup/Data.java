
package com.intellyze.recharge.cloud.response.topup;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("json")
    private Json mJson;

    public Json getJson() {
        return mJson;
    }

    public void setJson(Json json) {
        mJson = json;
    }

}
