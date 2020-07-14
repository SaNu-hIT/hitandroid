package com.intellyze.recharge.cloud.response.wallet

import com.google.gson.annotations.SerializedName

class WalletResponse {
    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("Token")
    var token: String? = null
}