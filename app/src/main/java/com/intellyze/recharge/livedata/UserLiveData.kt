package com.intellyze.recharge.livedata

import androidx.lifecycle.MutableLiveData

class UserLiveData: MutableLiveData<UserData>() {

    internal var mData = UserData()

    fun postError(throwable: ErrorData) {
        postValue(mData.error(throwable))
    }
    fun postLoggedIn() {
        postValue(mData.loginSuccess())
    }

    fun postRechargeSuccess(message:String) {
        postValue(mData.rechargeSuccess(message))
    }
    fun postRechargeFailed(message:String) {
        postValue(mData.rechargeFail(message))
    }





}