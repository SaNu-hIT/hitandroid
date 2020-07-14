
package com.intellyze.recharge.cloud.response.operators;


import com.google.gson.annotations.SerializedName;



public class Data {

    @SerializedName("json")
    private OperatorResponce mJson;

    public OperatorResponce getJson() {
        return mJson;
    }

    public void setJson(OperatorResponce json) {
        mJson = json;
    }

}
