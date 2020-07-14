package com.intellyze.recharge.model
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.intellyze.recharge.BR

data class LoginErrors(val errorName : String?) : BaseObservable() {

    var errorUserName: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorUserName)
            }

        }
    var errorPassword: String? = null
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
        errorUserName = errorName
        errorPassword = null
    }
}