package com.intellyze.recharge.cloud.response.transaction

import com.google.gson.annotations.SerializedName

class Transaction {
    @SerializedName("amount")
    var amount: String? = null
    @SerializedName("customIdentifier")
    var customIdentifier: String? = null
    @SerializedName("date")
    var date: String? = null
    @SerializedName("discount")
    var discount: String? = null
    @SerializedName("operatorId")
    var operatorId: String? = null
    @SerializedName("operatorName")
    var operatorName: String? = null
    @SerializedName("recipientCountryCode")
    var recipientCountryCode: String? = null
    @SerializedName("recipientNumber")
    var recipientNumber: String? = null
    @SerializedName("recipientPhone")
    var recipientPhone: String? = null
    @SerializedName("senderCountryCode")
    var senderCountryCode: String? = null
    @SerializedName("senderNumber")
    var senderNumber: String? = null
    @SerializedName("senderPhone")
    var senderPhone: String? = null
    @SerializedName("shopId")
    var shopId: String? = null
       @SerializedName("logoUrl")
    var logoUrl: String? = null
    @SerializedName("__v")
    private var m_V: Long? = null
    @SerializedName("_id")
    private var m_id: String? = null

    fun get_V(): Long? {
        return m_V
    }

    fun set_V(_V: Long?) {
        m_V = _V
    }

    fun get_id(): String? {
        return m_id
    }

    fun set_id(_id: String?) {
        m_id = _id
    }
}