package com.intellyze.recharge.cloud.response.alloperator
import com.google.gson.annotations.SerializedName
class Operator {
    @SerializedName("createdAt")
    var createdAt: String? = null
    @SerializedName("logoUrls")
    var logoUrls: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("operatorId")
    var operatorId: String? = null
    @SerializedName("type")
    var type: String? = null
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

