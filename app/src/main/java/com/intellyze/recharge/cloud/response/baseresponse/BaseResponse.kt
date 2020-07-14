package com.intellyze.recharge.cloud.response.baseresponse

import com.google.gson.annotations.SerializedName

class BaseResponse {
    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: Long? = null
    @SerializedName("Token")
    var token: String? = null

}