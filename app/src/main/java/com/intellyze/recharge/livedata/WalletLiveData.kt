package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData
import com.intellyze.recharge.cloud.response.wallet.Data

class WalletLiveData: MutableLiveData<Data>() {
    internal var mData = Data()

    fun postTransaction() {
        postValue(mData.postSuccess())
    }
}