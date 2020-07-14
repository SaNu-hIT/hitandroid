package com.intellyze.recharge.cloud.response.operators

import com.google.gson.annotations.SerializedName
import com.intellyze.recharge.livedata.UserData

class OperatorResponce {
    private var status: Int = 0
    fun getStatus(): Int {
        return status
    }
    fun postSuccess(): OperatorResponce {
        return this
    }
    fun postSuccessFailed(): OperatorResponce {
        this.status = 10
        return this
    }
    @SerializedName("bundle")
    var bundle: Boolean? = null
    @SerializedName("commission")
    var commission: Long? = null
    @SerializedName("country")
    var country: Country? = null
    @SerializedName("data")
    var data: Boolean? = null
    @SerializedName("denominationType")
    var denominationType: String? = null
    @SerializedName("destinationCurrencyCode")
    var destinationCurrencyCode: String? = null
    @SerializedName("destinationCurrencySymbol")
    var destinationCurrencySymbol: String? = null
    @SerializedName("fixedAmounts")
    var fixedAmounts: List<Double>? = null
    @SerializedName("fixedAmountsDescriptions")
    var fixedAmountsDescriptions: FixedAmountsDescriptions? = null
    @SerializedName("fx")
    var fx: Fx? = null
    @SerializedName("internationalDiscount")
    var internationalDiscount: Long? = null
    @SerializedName("localDiscount")
    var localDiscount: Long? = null
    @SerializedName("localFixedAmounts")
    var localFixedAmounts: List<Any>? = null
    @SerializedName("localFixedAmountsDescriptions")
    var localFixedAmountsDescriptions: LocalFixedAmountsDescriptions? = null
    @SerializedName("localMaxAmount")
    var localMaxAmount: Any? = null
    @SerializedName("localMinAmount")
    var localMinAmount: Any? = null
    @SerializedName("logoUrls")
    var logoUrls: List<String>? = null
    @SerializedName("maxAmount")
    var maxAmount: Any? = null
    @SerializedName("minAmount")
    var minAmount: Any? = null
    @SerializedName("mostPopularAmount")
    var mostPopularAmount: Double? = null
    @SerializedName("mostPopularLocalAmount")
    var mostPopularLocalAmount: Any? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("operatorId")
    var operatorId: Long? = null
    @SerializedName("pin")
    var pin: Boolean? = null
    @SerializedName("promotions")
    var promotions: List<Any>? = null
    @SerializedName("senderCurrencyCode")
    var senderCurrencyCode: String? = null
    @SerializedName("senderCurrencySymbol")
    var senderCurrencySymbol: String? = null
    @SerializedName("suggestedAmounts")
    var suggestedAmounts: List<Any>? = null
    @SerializedName("suggestedAmountsMap")
    var suggestedAmountsMap: SuggestedAmountsMap? = null
    @SerializedName("supportsLocalAmounts")
    var supportsLocalAmounts: Boolean? = null
}