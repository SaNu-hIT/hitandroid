package com.intellyze.recharge.utls
import android.content.Context
import android.text.InputFilter
import android.text.TextUtils
import com.intellyze.recharge.R

import java.util.regex.Pattern

class Validator(context: Context?) {
    var mContext: Context?;

    init {
        mContext = context;
    }
    fun isValidUserName(
        userName: String?,
        isFromLoginPage: Boolean
    ): String? {

        var errorMSg: String? = null;
        if (isNullOrEmpty(userName)) {
            errorMSg = mContext?.getString(R.string.invalid_user_name)
            return errorMSg;
        }
        if (userNameContainsSpecialCharacter(userName, isFromLoginPage)) {
            errorMSg = mContext?.getString(R.string.invalid_user_name_special_characters)
            return errorMSg;
        }
        if (userNameContainsSpace(userName)) {
            errorMSg = mContext?.getString(R.string.invalid_user_name_contains_space)
            return errorMSg;
        }
        if(!validateUserNameLength(userName, isFromLoginPage)){
            errorMSg = mContext?.getString(R.string.invalid_user_name_length)
            return errorMSg;
        }
        return errorMSg;
    }

    fun validateUserNameLength(userName: String?, isFromLoginPage: Boolean): Boolean {
        var maxLength = 15
        var minLength = 3
        if (isFromLoginPage) {
            maxLength = 31
            minLength = 7
        }
        return isValidLength(userName, minLength, maxLength);
    }

    fun userNameContainsSpecialCharacter(value: String?, isFromLoginPage: Boolean): Boolean {
        var p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        if (isFromLoginPage)
            p = Pattern.compile("[^a-z0-9@ ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(value)
        val b = m.find()
        return b
    }

    fun userNameContainsSpace(value: String?): Boolean {
        return value!!.contains(" ")
    }

    fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }

    fun isValidUserPassword(password: String?): String? {
        var errorMSg: String? = null;
        if (isNullOrEmpty(password)) {
            errorMSg = mContext?.getString(R.string.invalid_password)
            return errorMSg;
        }
        if (hasSpace(password)) {
            errorMSg = mContext?.getString(R.string.invalid_password_with_space)
            return errorMSg;
        }
        if (!hasNumericValues(password)) {
            errorMSg = mContext?.getString(R.string.invalid_password_not_alpha_numeric)
            return errorMSg;
        }
        if(!containsAtleastOneAlphabet(password)){
            errorMSg = mContext?.getString(R.string.invalid_password_not_alpha_numeric)
            return errorMSg;
        }
        if(!validateUserPasswordLength(password)){
            errorMSg = mContext?.getString(R.string.invalid_password_length)
            return errorMSg;
        }
        return errorMSg;
    }

    fun isValidUserPassword(password: String?,confirmPassword: String?): String? {
        var errorMSg: String? = null;
        if (!password.equals(confirmPassword)) {
            errorMSg = mContext?.getString(R.string.invalid_password_equal)
            return errorMSg
        }

        return errorMSg;
    }

    private fun containsAtleastOneAlphabet(s: String?): Boolean {

        if (s != null) {
            return  s.matches(".*[a-zA-Z]+.*".toRegex())
        }
        return false
    }

    fun hasSpace(value: String?): Boolean {
        if (value != null) {
            return value.matches("^ +.*".toRegex())
        }
        return true
    }

    private fun hasNumericValues(value: String?): Boolean {
        if (value != null) {
            return value.matches(".*\\d+.*".toRegex())
        }
        return true
    }

    fun validateUserPasswordLength(password: String?): Boolean {
        var maxLength = 30
        var minLength = 6

        return isValidLength(password, minLength, maxLength);
    }

    fun isValidLength(value: String?, minLength : Int, maxLength : Int): Boolean {

        if (value != null) {
            if (value.length < minLength || value.length > maxLength) {
                return false;
            }
        }
        return true;
    }


    private fun hasSpecialCharacters(value: String?): Boolean {
        var p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)
        val m = p.matcher(value)
        val b = m.find()
        return b
    }

    fun validateEmail(email: String?): String? {
        var errorMSg: String? = null
        if (isNullOrEmpty(email) || isValidEmail(email) == false) {
            errorMSg = mContext?.getString(R.string.invalid_email_pattern_not_matched)
            return errorMSg;
        }
        val separated = email!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (separated == null || separated.size < 2 || separated[separated.size - 1].length < 2) {
            errorMSg = mContext?.getString(R.string.invalid_email)
            return errorMSg;
        }

        if (Character.isWhitespace(email[0]) || Character.isWhitespace(email[email.length - 1])) {
            errorMSg = mContext?.getString(R.string.invalid_email)
            return errorMSg;
        }

        return errorMSg;
    }
    fun isValidEmail(target: CharSequence?): Boolean {

        val emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        return target?.toString()?.matches(emailPattern.toRegex())?: false
    }



    fun isValidPhone(phone : String?): String?{
        var errorMSg: String? = null;
        if (isNullOrEmpty(phone)) {
            errorMSg = mContext?.getString(R.string.invalid_phone)
            return errorMSg;
        }

        if(!isValidLength(phone, 10, 10)){
            errorMSg = mContext?.getString(R.string.invalid_phone)
            return errorMSg;
        }
        return errorMSg;
    }

    fun isValidAmount(amount: String?): String? {
        var errorMSg: String? = null;
        if (isNullOrEmpty(amount)) {
            errorMSg = mContext?.getString(R.string.invalid_amount)
            return errorMSg;
        }

        return errorMSg;
    }

    fun isOperatiorIdIsValid(operatorId: String?): String? {
        var errorMSg: String? = null;
        if (isNullOrEmpty(operatorId)) {
            errorMSg = mContext?.getString(R.string.invalid_operatorId)
            return errorMSg;
        }

        return errorMSg;
    }

}