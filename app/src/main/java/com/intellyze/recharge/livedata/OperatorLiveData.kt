package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData

import com.intellyze.recharge.cloud.response.operators.OperatorResponce

class OperatorLiveData: MutableLiveData<OperatorResponce>() {
    internal var mData = OperatorResponce()

    fun postOperator() {
        postValue(mData.postSuccess())
    }

    fun postOperatorFailed() {
        postValue(mData.postSuccessFailed())
    }
}