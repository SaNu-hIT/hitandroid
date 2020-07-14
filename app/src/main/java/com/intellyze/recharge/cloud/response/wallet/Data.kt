package com.intellyze.recharge.cloud.response.wallet

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("wallet")
    var wallet: List<Wallet>? = null

    var trans: List<Wallet>? =
        null
    fun postSuccess(): Data {
        this.trans = wallet
        return this
    }

}