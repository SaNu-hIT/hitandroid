package com.intellyze.recharge.model
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.intellyze.recharge.BR

data class RechargeErrors(val errorName : String?) : BaseObservable() {

    var errorNumber: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorUserName)
            }

        }
    var errrorOperator: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorPassword)
            }

        }
    var errorAmount: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorPassword)
            }

        }
    var uiUpdate: Boolean? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.uiUpdate)
            }        }

    init {
        errorNumber = errorName
        errrorOperator = null
        errorAmount = null
    }
}