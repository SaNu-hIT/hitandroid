package com.intellyze.recharge.model
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.intellyze.recharge.BR

data class SignUpErrors(val errorName : String?) : BaseObservable() {

    var errorUserName: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorUserName)
            }

        }
    var errorOrganizationName: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorOrganizationName)
            }

        }
    var errorEmail: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorEmail)
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
    var errorConfirmPassword: String? = null
        @Bindable
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.errorConfirmPassword)
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
        errorUserName = errorName;
    }
}