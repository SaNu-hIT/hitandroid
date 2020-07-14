package com.intellyze.recharge.view.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.intellyze.recharge.cloud.request.recharge.MobileNumberRequest
import com.intellyze.recharge.cloud.request.recharge.OperatorNameRequest
import com.intellyze.recharge.cloud.request.user.WebService
import com.intellyze.recharge.cloud.request.user.login.UserLoginRequest
import com.intellyze.recharge.cloud.response.alloperator.AllOperatorResponse
import com.intellyze.recharge.cloud.response.plans.PlansResponse
import com.intellyze.recharge.database.room.repo.OperatorRepository
import com.intellyze.recharge.database.room.repo.PlansRepository
import com.intellyze.recharge.livedata.ErrorData
import com.intellyze.recharge.livedata.UserData
import com.intellyze.recharge.livedata.UserLiveData
import com.intellyze.recharge.model.LoginData
import com.intellyze.recharge.model.LoginErrors
import com.intellyze.recharge.utls.AppSharedPreferance
import com.intellyze.recharge.utls.Validator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LogInViewModel(context: Application) : AndroidViewModel(context) {
    var signUpValidator: Validator;
    var mContext: Context?;
    var loginData: LoginData? = null;
    var loginServices: WebService? = null;
    var mPref: AppSharedPreferance? = null;
    var operatorRepository: OperatorRepository? = null
    var plansRepository: PlansRepository? = null
    internal var userLiveData: UserLiveData? = null

    init {
        mContext = context
        signUpValidator = Validator(mContext)
        userLiveData = UserLiveData()
        userLiveData?.mData = UserData()
        operatorRepository = OperatorRepository(context)
        plansRepository = PlansRepository(context)
    }

    fun doLogin(loginData: LoginData, errorModel: LoginErrors) {

        this.loginData = loginData;
        errorModel.errorUserName = signUpValidator.isValidPhone(loginData.username);
        errorModel.errorPassword = signUpValidator.isValidUserPassword(loginData.password);
        if (isNullOrEmpty(errorModel.errorUserName)
            && isNullOrEmpty(errorModel.errorPassword)
        ) {
            errorModel.uiUpdate = true;
            callSiginUps(loginData, errorModel);
        }
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }

    @SuppressLint("CheckResult")
    fun callSiginUps(loginData: LoginData, errorModel: LoginErrors) {
        errorModel.uiUpdate = true
        loginServices = WebService.create()
        loginServices?.doShopLogin(getLoginRequest(loginData))
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status == 1000L) {
                    mPref?.setStringPrefValue(
                        AppSharedPreferance.Constants.SHOP_ID,
                        result.data?.userId
                    )
                    mPref?.setStringPrefValue(
                        AppSharedPreferance.Constants.PHONE_NUMBER,
                        result.data?.phone
                    )
                    mPref?.setStringPrefValue(
                        AppSharedPreferance.Constants.MY_WEB_SERVICE_TOKEN,
                        result.token
                    )

                    getAllPlans(errorModel)

                } else {
                    userLiveData?.postError(ErrorData(result.status, result.message))
                    errorModel.uiUpdate = false
                }
            }, { error ->
                errorModel.uiUpdate = false
                userLiveData?.postError(ErrorData(401, "error"))
                error.printStackTrace()
                error.message
            });
    }

    fun getLoginRequest(loginData: LoginData): UserLoginRequest {
        var request =
            UserLoginRequest()
        request.username = loginData.username
        request.password = loginData.password
        if (null == mPref)
            mPref = AppSharedPreferance(mContext);
        request.device_token =
            mPref?.getIntegerPrefValue(AppSharedPreferance.Constants.APP_ID).toString()
        request.device_token = "update phone id "
        return request
    }


    fun getUserLiveData(): UserLiveData? {
        return userLiveData

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

    @SuppressLint("CheckResult")
    fun getAllPlans(errorModel: LoginErrors) {


        deleteModel()
        loginServices = WebService.create()
        val op = OperatorNameRequest()
        op.operator = ""
        loginServices?.getPlans(op)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status == "1000") {
                    insertPlans(result, errorModel)
                }
            }, { error ->
                error.printStackTrace()
            })
    }


    @SuppressLint("CheckResult")
    fun getAllOperators(errorModel: LoginErrors) {
        loginServices = WebService.create()
        var mobileIdRequest = MobileNumberRequest()
        mobileIdRequest.phone = ""
        loginServices?.getAllOperator(mobileIdRequest)
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({ result ->
                if (result.status == "1000") {

                    insertOperator(result, errorModel)

                }
            },
                { error ->
                    error.printStackTrace()
                    error.message
                });
    }


    private fun insertPlans(
        result: PlansResponse?,
        errorModel: LoginErrors
    ) {

        var planList: ArrayList<com.intellyze.recharge.database.model.DbPlans>? = null
        var plans = result?.data?.plans

        if (plans != null) {
            planList = ArrayList()
            for (p in plans) {

                var op = com.intellyze.recharge.database.model.DbPlans()
                op.description = p?.description
                op.operator = p?.operator
                op.planType = p?.planType
                op.validity = p?.validity
                op.price = p?.price
                op.talktime = p?.talktime
                planList?.add(op)
            }
        }
        if (planList != null) {

            var async = object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
                    plansRepository?.plansDao?.insert(planList)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    getAllOperators(errorModel)
                }
            }
            async.execute()


        }

    }


    private fun insertOperator(
        result: AllOperatorResponse?,
        errorModel: LoginErrors
    ) {

        var oplist: ArrayList<com.intellyze.recharge.database.model.DbOperator>? =
            null
        var operator = result?.data?.operators

        if (operator != null) {
            oplist = ArrayList()
            for (p in operator) {
                var op = com.intellyze.recharge.database.model.DbOperator()
                op.logoUrls = p?.logoUrls
                op.operatorId = p?.operatorId
                op.name = p?.name
                op.type = p?.type
                oplist?.add(op)
            }
        }

        if (oplist != null) {

            var async = object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
                    operatorRepository?.operatorDao?.insert(oplist)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    errorModel.uiUpdate = false
                    userLiveData?.postLoggedIn()
                }
            }
            async.execute()

        }

    }


}
