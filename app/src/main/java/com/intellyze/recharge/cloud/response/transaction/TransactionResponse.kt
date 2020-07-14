package com.intellyze.recharge.cloud.response.transaction

import com.google.gson.annotations.SerializedName

class TransactionResponse {
    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("Token")
    var token: String? = null

}