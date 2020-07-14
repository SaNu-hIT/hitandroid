package com.intellyze.recharge.cloud.response.shoplogin

import com.google.gson.annotations.SerializedName

class LoginRes {
    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: Long? = null
    @SerializedName("Token")
    var token: String? = null

}