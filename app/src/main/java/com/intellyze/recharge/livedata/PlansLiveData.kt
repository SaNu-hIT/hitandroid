package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData

import com.intellyze.recharge.cloud.response.plans.PlansResponse

class PlansLiveData: MutableLiveData<PlansResponse>() {
    internal var mData = PlansResponse()

    fun postOperator() {
        postValue(mData.postSuccess())
    }
}