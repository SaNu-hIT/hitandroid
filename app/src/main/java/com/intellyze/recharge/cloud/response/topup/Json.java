
package com.intellyze.recharge.cloud.response.topup;


import com.google.gson.annotations.SerializedName;


public class Json {

    @SerializedName("balanceInfo")
    private BalanceInfo mBalanceInfo;
    @SerializedName("countryCode")
    private String mCountryCode;
    @SerializedName("customIdentifier")
    private Object mCustomIdentifier;
    @SerializedName("deliveredAmount")
    private Long mDeliveredAmount;
    @SerializedName("deliveredAmountCurrencyCode")
    private String mDeliveredAmountCurrencyCode;
    @SerializedName("discount")
    private Double mDiscount;
    @SerializedName("discountCurrencyCode")
    private String mDiscountCurrencyCode;
    @SerializedName("operatorId")
    private Long mOperatorId;
    @SerializedName("operatorName")
    private String mOperatorName;
    @SerializedName("operatorTransactionId")
    private Object mOperatorTransactionId;
    @SerializedName("pinDetail")
    private Object mPinDetail;
    @SerializedName("recipientPhone")
    private String mRecipientPhone;
    @SerializedName("requestedAmount")
    private Long mRequestedAmount;
    @SerializedName("requestedAmountCurrencyCode")
    private String mRequestedAmountCurrencyCode;
    @SerializedName("senderPhone")
    private String mSenderPhone;
    @SerializedName("transactionDate")
    private String mTransactionDate;
    @SerializedName("transactionId")
    private Long mTransactionId;

    public BalanceInfo getBalanceInfo() {
        return mBalanceInfo;
    }

    public void setBalanceInfo(BalanceInfo balanceInfo) {
        mBalanceInfo = balanceInfo;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public Object getCustomIdentifier() {
        return mCustomIdentifier;
    }

    public void setCustomIdentifier(Object customIdentifier) {
        mCustomIdentifier = customIdentifier;
    }

    public Long getDeliveredAmount() {
        return mDeliveredAmount;
    }

    public void setDeliveredAmount(Long deliveredAmount) {
        mDeliveredAmount = deliveredAmount;
    }

    public String getDeliveredAmountCurrencyCode() {
        return mDeliveredAmountCurrencyCode;
    }

    public void setDeliveredAmountCurrencyCode(String deliveredAmountCurrencyCode) {
        mDeliveredAmountCurrencyCode = deliveredAmountCurrencyCode;
    }

    public Double getDiscount() {
        return mDiscount;
    }

    public void setDiscount(Double discount) {
        mDiscount = discount;
    }

    public String getDiscountCurrencyCode() {
        return mDiscountCurrencyCode;
    }

    public void setDiscountCurrencyCode(String discountCurrencyCode) {
        mDiscountCurrencyCode = discountCurrencyCode;
    }

    public Long getOperatorId() {
        return mOperatorId;
    }

    public void setOperatorId(Long operatorId) {
        mOperatorId = operatorId;
    }

    public String getOperatorName() {
        return mOperatorName;
    }

    public void setOperatorName(String operatorName) {
        mOperatorName = operatorName;
    }

    public Object getOperatorTransactionId() {
        return mOperatorTransactionId;
    }

    public void setOperatorTransactionId(Object operatorTransactionId) {
        mOperatorTransactionId = operatorTransactionId;
    }

    public Object getPinDetail() {
        return mPinDetail;
    }

    public void setPinDetail(Object pinDetail) {
        mPinDetail = pinDetail;
    }

    public String getRecipientPhone() {
        return mRecipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        mRecipientPhone = recipientPhone;
    }

    public Long getRequestedAmount() {
        return mRequestedAmount;
    }

    public void setRequestedAmount(Long requestedAmount) {
        mRequestedAmount = requestedAmount;
    }

    public String getRequestedAmountCurrencyCode() {
        return mRequestedAmountCurrencyCode;
    }

    public void setRequestedAmountCurrencyCode(String requestedAmountCurrencyCode) {
        mRequestedAmountCurrencyCode = requestedAmountCurrencyCode;
    }

    public String getSenderPhone() {
        return mSenderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        mSenderPhone = senderPhone;
    }

    public String getTransactionDate() {
        return mTransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        mTransactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return mTransactionId;
    }

    public void setTransactionId(Long transactionId) {
        mTransactionId = transactionId;
    }

}
