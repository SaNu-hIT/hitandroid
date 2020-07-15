package com.intellyze.recharge.view.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.intellyze.recharge.cloud.response.transaction.Data
import com.intellyze.recharge.cloud.request.recharge.ShopIdRequest
import com.intellyze.recharge.cloud.request.user.WebService
import com.intellyze.recharge.database.room.repo.OperatorRepository
import com.intellyze.recharge.database.room.repo.PlansRepository
import com.intellyze.recharge.livedata.TransactionLiveData
import com.intellyze.recharge.livedata.WalletLiveData
import com.intellyze.recharge.model.LoginData
import com.intellyze.recharge.utls.AppSharedPreferance
import com.intellyze.recharge.utls.Validator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(context: Application) : AndroidViewModel(context) {
    var signUpValidator: Validator;
    var mContext: Context?;
    var loginData: LoginData? = null;
    var loginServices: WebService? = null;
    var mPref: AppSharedPreferance? = null;
    internal var userLiveData: TransactionLiveData? = null
    internal var walletLiveData: WalletLiveData? = null
    var operatorRepository: OperatorRepository? = null
    var plansRepository: PlansRepository? = null


    init {
        mContext = context
        signUpValidator = Validator(mContext)
        userLiveData = TransactionLiveData()
        walletLiveData = WalletLiveData()
        userLiveData?.mData = Data()
        operatorRepository = OperatorRepository(context)
        plansRepository = PlansRepository(context)
        mPref = AppSharedPreferance(mContext);
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }


    @SuppressLint("CheckResult")
    fun getTransactions() {
        var shopId = mPref?.getStringPrefValue(AppSharedPreferance.Constants.SHOP_ID)
         loginServices = WebService.create()
         var shopIdRequest = ShopIdRequest()
        shopIdRequest.shopId=shopId
        println("Shop id $shopId")
        loginServices?.getTransactions(shopIdRequest)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status.equals(
                        "1000"
                    )
                ) {
                    userLiveData?.mData = result.data!!
                    userLiveData?.postTransaction()
                } else {
                    userLiveData?.postTransaction()
                }
            },
                { error ->
                    error.printStackTrace()
                    error.message
                })
    }
    fun getTransactionLiveData(): TransactionLiveData? {
        return userLiveData
    }    fun getWalletLiveData(): WalletLiveData? {
        return walletLiveData
    }
    fun getWalletAmount() {
        var shopId = mPref?.getStringPrefValue(AppSharedPreferance.Constants.SHOP_ID)
        loginServices = WebService.create()
        var shopIdRequest = ShopIdRequest()
        shopIdRequest.shopId=shopId
        loginServices?.getWallet(shopIdRequest)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status.equals(
                        "1000"
                    )
                ) {
                    walletLiveData?.mData = result.data!!
                    walletLiveData?.postTransaction()
                } else {
                    walletLiveData?.postTransaction()
                }
            },
                { error ->
                    error.printStackTrace()
                    error.message
                });

    }

    fun logOut() {

        mPref?.clear()
        deleteModel()
    }
    fun deleteModel() {

        var async = object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                operatorRepository?.operatorDao?.deleteAll()
                plansRepository?.plansDao?.deleteAll()
                return null
            }

            override fun onPostExecute(result: Void?) {
                return
            }
        }
        async.execute()


    }
}
