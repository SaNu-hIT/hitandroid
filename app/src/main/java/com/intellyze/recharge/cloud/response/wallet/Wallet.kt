package com.intellyze.recharge.cloud.response.wallet

import com.google.gson.annotations.SerializedName

class Wallet {
    @SerializedName("createdAt")
    var createdAt: String? = null
    @SerializedName("shopId")
    var shopId: String? = null
    @SerializedName("wallet_amount")
    var walletAmount: String? = null
    @SerializedName("wallet_balance")
    var walletBalance: String? = null
    @SerializedName("__v")
    private var m_V: String? = null
    @SerializedName("_id")
    private var m_id: String? = null

    fun get_V(): String? {
        return m_V
    }

    fun set_V(_V: String?) {
        m_V = _V
    }

    fun get_id(): String? {
        return m_id
    }

    fun set_id(_id: String?) {
        m_id = _id
    }
}