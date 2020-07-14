package com.intellyze.recharge.cloud.response.plans
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("plans")
    var plans: List<Plans>? = null

    fun postSuccessData(): Data {
        return this
    }
}