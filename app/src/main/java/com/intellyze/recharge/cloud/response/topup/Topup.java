
package com.intellyze.recharge.cloud.response.topup;


import com.google.gson.annotations.SerializedName;


public class Topup {

    @SerializedName("Data")
    private Data mData;
    @SerializedName("Message")
    private String mMessage;
    @SerializedName("Status")
    private String mStatus;
    @SerializedName("Token")
    private String mToken;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

}
