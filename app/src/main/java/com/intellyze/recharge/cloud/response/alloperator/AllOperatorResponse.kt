package com.intellyze.recharge.cloud.response.alloperator

import com.google.gson.annotations.SerializedName

class AllOperatorResponse {
    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("Token")
    var token: String? = null
    fun postSuccess(): AllOperatorResponse? {
        return this
    }

}