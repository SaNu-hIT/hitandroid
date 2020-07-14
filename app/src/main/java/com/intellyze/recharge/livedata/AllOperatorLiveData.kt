package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData
import com.intellyze.recharge.cloud.response.alloperator.AllOperatorResponse


class AllOperatorLiveData: MutableLiveData<AllOperatorResponse>() {
    internal var mData = AllOperatorResponse()

    fun postOperator() {
        postValue(mData.postSuccess())
    }
}