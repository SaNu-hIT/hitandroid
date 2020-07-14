package com.intellyze.recharge.cloud.response.shoplogin

import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("user_id")
    var userId: String? = null
    @SerializedName("phone")
    var phone: String? = null
}