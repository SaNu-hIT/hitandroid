package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData
import com.intellyze.recharge.cloud.response.transaction.Data

class TransactionLiveData: MutableLiveData<Data>() {
    internal var mData = Data()

    fun postTransaction() {
        postValue(mData.postSuccess())
    }
}