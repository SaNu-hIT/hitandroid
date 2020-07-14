
package com.intellyze.recharge.cloud.response.topup;


import com.google.gson.annotations.SerializedName;


public class BalanceInfo {

    @SerializedName("currencyCode")
    private String mCurrencyCode;
    @SerializedName("currencyName")
    private String mCurrencyName;
    @SerializedName("newBalance")
    private Double mNewBalance;
    @SerializedName("oldBalance")
    private Double mOldBalance;
    @SerializedName("updatedAt")
    private String mUpdatedAt;

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        mCurrencyName = currencyName;
    }

    public Double getNewBalance() {
        return mNewBalance;
    }

    public void setNewBalance(Double newBalance) {
        mNewBalance = newBalance;
    }

    public Double getOldBalance() {
        return mOldBalance;
    }

    public void setOldBalance(Double oldBalance) {
        mOldBalance = oldBalance;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
