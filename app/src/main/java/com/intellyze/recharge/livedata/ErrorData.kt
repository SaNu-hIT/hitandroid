package com.intellyze.recharge.livedata

class ErrorData {
    internal var errorCode: Long? = 0
    internal var errorMessage: String?

    constructor(errorCode: Long?, errorMessage: String?) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage
    }

    fun getErrorCode(): Long? {
        return errorCode
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }
}