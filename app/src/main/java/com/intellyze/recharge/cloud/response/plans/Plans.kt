package com.intellyze.recharge.cloud.response.plans
import com.google.gson.annotations.SerializedName

class Plans {
    @SerializedName("createdAt")
    var createdAt: String? = null
    @SerializedName("Description")
    var description: String? = null
    @SerializedName("operator")
    var operator: String? = null
    @SerializedName("Plan_type")
    var planType: String? = null
    @SerializedName("Price")
    var price: String? = null
    @SerializedName("Talktime")
    var talktime: String? = null
    @SerializedName("Validity")
    var validity: String? = null
    @SerializedName("__v")
    private var m_V: Long? = null
    @SerializedName("_id")
    private var m_id: String? = null

    fun get_V(): Long? {
        return m_V
    }

    fun set_V(_V: Long?) {
        m_V = _V
    }

    fun get_id(): String? {
        return m_id
    }

    fun set_id(_id: String?) {
        m_id = _id
    }
}