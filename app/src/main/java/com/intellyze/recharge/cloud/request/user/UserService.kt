package com.intellyze.recharge.cloud.request.user
import android.text.TextUtils
import com.intellyze.recharge.cloud.response.wallet.WalletResponse
import com.intellyze.recharge.cloud.request.recharge.MobileNumberRequest
import com.intellyze.recharge.cloud.request.recharge.OperatorNameRequest
import com.intellyze.recharge.cloud.request.recharge.ShopIdRequest
import com.intellyze.recharge.cloud.request.user.login.UserLoginRequest
import com.intellyze.recharge.cloud.response.alloperator.AllOperatorResponse
import com.intellyze.recharge.model.MobileRechargeData
import com.intellyze.recharge.cloud.retrofit.RetrofitClient
import com.intellyze.recharge.cloud.response.baseresponse.BaseResponse
import com.intellyze.recharge.cloud.response.operators.OperatorRes
import com.intellyze.recharge.cloud.response.plans.PlansResponse
import com.intellyze.recharge.cloud.response.shoplogin.LoginRes
import com.intellyze.recharge.cloud.response.topup.Topup
import com.intellyze.recharge.cloud.response.transaction.TransactionResponse
import io.reactivex.Observable
import retrofit2.http.*

interface WebService {

    @Headers(
        "X-Powered-By: Express",
        "Content-Type: application/json; charset=utf-8"
    )
    @POST("shop/shoplogin")
    fun doShopLogin(@Body request: UserLoginRequest): Observable<LoginRes>

    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("topups/topups")
    fun topUp(@Body request: MobileRechargeData): Observable<Topup>

    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("transactions/gettransactions")
    fun getTransactions(@Body request: ShopIdRequest): Observable<TransactionResponse>


    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("operators/operators")
    fun getOperator(@Body request: MobileNumberRequest): Observable<OperatorRes>

    @Headers(
        "X-Powered-By: Express",
        "Content-Type: application/json; charset=utf-8"
        ,"Connection: keep-alive"
    )
    @POST("operators/alloperators")
    fun getAllOperator(@Body request: MobileNumberRequest): Observable<AllOperatorResponse>

    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("transactions/getwallet")
    fun getWallet(@Body request: ShopIdRequest): Observable<WalletResponse>


    @Headers(
        "X-Powered-By: Express",
        "Content-Type: application/json; charset=utf-8",
        "Connection: keep-alive"
    )
    @POST("operators/getPlans")
    fun getPlans(@Body request: OperatorNameRequest): Observable<PlansResponse>



    companion object Factory {
        fun create(): WebService? {
            var url = "https://rechargemylife.herokuapp.com/"
            val connector = Cloud.mCloudConnector
            if(null != connector && !TextUtils.isEmpty(connector.getBaseUrl())){
                url = connector.getBaseUrl()
            }
            val retrofit = RetrofitClient.getRetrofitClient(url)
            return retrofit?.create(WebService::class.java)
        }
    }
}