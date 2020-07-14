package com.intellyze.recharge.livedata

class UserData {
    private var status: Int = 0
    private var cloudId: Long? = 0
    private var message: String= ""
    private var error: ErrorData? = null
    init {
        this.error = null
    }
    fun error(error: ErrorData): UserData {
        this.status = UserStatus.ERROR
        this.error = error
        return this
    }
    fun getStatus(): Int {
        return status
    }
    fun getMessage(): String {
        return message
    }
    fun getError(): ErrorData? {
        return error
    }

    fun loginSuccess(): UserData {
        this.status = UserStatus.LOGIN_SUCCESS
        this.error = null
        return this
    }

    fun rechargeSuccess(message:String): UserData {
        this.status = UserStatus.RECHARGE_SUCCESS
        this.error = null
        this.message = message
        return this
    }
    fun rechargeFail(message:String): UserData {
        this.status = UserStatus.RECHARGE_FAIL
        this.error = null
        this.message = message
        return this
    }


    fun siginSuccess(): UserData {
        this.status = UserStatus.SIGIN_SUCCESS
        this.error = null
        return this
    }




    interface UserStatus {
        companion object {
            val SIGIN_SUCCESS = 0
            val LOGIN_SUCCESS = 1
            val RECHARGE_SUCCESS = 2
            val RECHARGE_FAIL = 3
            val ERROR = 4


        }
    }
    internal interface UserTypes {
        companion object {
            val ADMIN = 1
            val MANAGER = 2

        }
    }
}