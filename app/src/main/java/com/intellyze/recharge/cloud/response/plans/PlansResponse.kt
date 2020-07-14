package com.intellyze.recharge.cloud.response.plans
import com.google.gson.annotations.SerializedName

class PlansResponse {


    @SerializedName("Data")
    var data: Data? = null
    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("Token")
    var token: String? = null

    fun postSuccess(): PlansResponse {
        return this
    }

}