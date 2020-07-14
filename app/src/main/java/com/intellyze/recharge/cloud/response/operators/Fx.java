
package com.intellyze.recharge.cloud.response.operators;


import com.google.gson.annotations.SerializedName;



public class Fx {

    @SerializedName("currencyCode")
    private String mCurrencyCode;
    @SerializedName("rate")
    private Double mRate;

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public Double getRate() {
        return mRate;
    }

    public void setRate(Double rate) {
        mRate = rate;
    }

}
