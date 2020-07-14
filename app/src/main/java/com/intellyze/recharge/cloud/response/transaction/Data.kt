package com.intellyze.recharge.cloud.response.transaction

import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("transactions")
    var transactions: List<Transaction>? =
        null

    var trans: List<Transaction>? =
        null

    fun postSuccess(): Data {
        this.trans = transactions
        return this
    }
}