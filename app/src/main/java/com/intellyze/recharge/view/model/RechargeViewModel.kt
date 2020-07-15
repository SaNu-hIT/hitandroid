package com.intellyze.recharge.view.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.intellyze.recharge.cloud.request.recharge.MobileNumberRequest
import com.intellyze.recharge.cloud.request.recharge.OperatorNameRequest
import com.intellyze.recharge.cloud.request.user.WebService
import com.intellyze.recharge.cloud.response.alloperator.Operator

import com.intellyze.recharge.cloud.response.operators.OperatorResponce
import com.intellyze.recharge.cloud.response.plans.PlansResponse
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.database.room.repo.OperatorRepository
import com.intellyze.recharge.database.room.repo.PlansRepository
import com.intellyze.recharge.livedata.*
import com.intellyze.recharge.model.MobileRechargeData
import com.intellyze.recharge.model.RechargeErrors
import com.intellyze.recharge.utls.AppSharedPreferance
import com.intellyze.recharge.utls.Validator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RechargeViewModel(context: Application) : AndroidViewModel(context) {
    var validator: Validator;
    var mContext: Context?;
    var rechargeData: MobileRechargeData? = null;
    var services: WebService? = null;
    var mPref: AppSharedPreferance? = null;
    internal var userLiveData: UserLiveData? = null
    internal var operatorData: OperatorLiveData? = null
    internal var plansLiveData: PlansLiveData? = null


    var operatorRepository: OperatorRepository? = null
    var plansRepository: PlansRepository? = null
    init {
        mContext = context
        validator = Validator(mContext)
        userLiveData = UserLiveData()
        operatorData = OperatorLiveData()
        plansLiveData = PlansLiveData()
        operatorData?.mData = OperatorResponce()
        plansLiveData?.mData = PlansResponse()
        operatorRepository = OperatorRepository(context)
        plansRepository = PlansRepository(context)
        mPref = AppSharedPreferance(context)
    }


    var dbPlans : LiveData<PagedList<DbPlans>>? = null
    fun browsePlans(data: MobileRechargeData, errorModel: RechargeErrors):LiveData<PagedList<DbPlans>>? {

       if(data.operatorName!=null && data.operatorName!="")
       {
           errorModel.uiUpdate = true
           var opname = ""
           if(data.operatorName=="IDEA India"){
               opname = "Idea"
           }else   if(data.operatorName=="Reliance Jio India"){
               opname = "Jio"
           }
           else   if(data.operatorName=="BSNL India"){
               opname = "BSNL"
           }else   if(data.operatorName=="Airtel India"){
               opname = "Airtel"
           }

           dbPlans = plansRepository?.getPlansByOperator(opname)
           errorModel.uiUpdate = false

       }

        return dbPlans
    }


    fun doRecharge(data: MobileRechargeData, errorModel: RechargeErrors) {

        data.customIdentifier = ""
        data.discount = "0.0"
        data.recipientCountryCode = "IN"
        data.senderCountryCode = "IN"
        data.senderNumber = mPref?.getStringPrefValue(AppSharedPreferance.Constants.PHONE_NUMBER)
        data.senderPhone = mPref?.getStringPrefValue(AppSharedPreferance.Constants.PHONE_NUMBER)
        data.shopId = mPref?.getStringPrefValue(AppSharedPreferance.Constants.SHOP_ID)
        println("shop id"+data.shopId)
        this.rechargeData = data
        errorModel.errorNumber = validator.isValidPhone(rechargeData?.recipientPhone);
        errorModel.errorAmount = validator.isValidAmount(rechargeData?.amount);
        errorModel.errrorOperator = validator.isOperatiorIdIsValid(rechargeData?.operatorId);

        if (isNullOrEmpty(errorModel.errorNumber)
            && isNullOrEmpty(errorModel.errorAmount)
        ) {
            errorModel.uiUpdate = true
            rechargeData?.let { callRecharge(it, errorModel) };
        }else{
            errorModel.uiUpdate = false
             userLiveData?.postRechargeFailed("Please check the mobile number and amount.")


        }
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }

    @SuppressLint("CheckResult")
    fun getOperators(mobile: String?) {
        services = WebService.create()
        var mobileIdRequest = MobileNumberRequest()
        mobileIdRequest.phone = mobile
        services?.getOperator(mobileIdRequest)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if(null!=operatorData) {
                    if(null!=result.data.json)
                    {
                        operatorData?.mData = result.data.json
                        operatorData?.postOperator()
                    }else{
                     operatorData?.postOperatorFailed()
                    }


                }

            },
                { error ->
                    error.printStackTrace()
                    error.message
                });
    }
    @SuppressLint("CheckResult")
    fun callRecharge(data: MobileRechargeData, errorModel: RechargeErrors) {
        services = WebService.create()
        services?.topUp(data)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status == "1000") {
                    errorModel.uiUpdate = false
                    userLiveData?.postRechargeSuccess(result.message)
                } else {
                    errorModel.uiUpdate = false
                    userLiveData?.postRechargeFailed(result.message)
                }
            }, { error ->
                userLiveData?.postError(ErrorData(400, "Api error"))
                errorModel.uiUpdate = false
                error.printStackTrace()
            })
    }


    @SuppressLint("CheckResult")
    fun getPlan(data: MobileRechargeData, errorModel: RechargeErrors) {
        services = WebService.create()
        val op = OperatorNameRequest()
        op.operator = data.operatorName.toString()
        services?.getPlans(op)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status == "1000") {

                    plansLiveData?.mData = result
                    plansLiveData?.postOperator()
                    errorModel.uiUpdate = false
                } else {
                    errorModel.uiUpdate = false
//                    userLiveData?.postRechargeFailed()
                }
            }, { error ->
//                userLiveData?.postError(ErrorData(400, "Api error"))
                errorModel.uiUpdate = false
                error.printStackTrace()
            });
    }

    fun getUserLiveData(): UserLiveData? {
        return userLiveData

    }

    fun getOperatorLiveData(): OperatorLiveData? {
        return operatorData

    }   fun getPlansLiveData(): PlansLiveData? {
        return plansLiveData

    }

var dbOperators :LiveData<PagedList<DbOperator>>? = null

    fun getAllOperator(): LiveData<PagedList<DbOperator>>? {
        dbOperators = operatorRepository?.getAllOperaor()
        return dbOperators

    }

    fun getOperatorById(type:String): LiveData<PagedList<DbOperator>>? {
        dbOperators = operatorRepository?.getOperatorById(type)
        return dbOperators

    }
}
