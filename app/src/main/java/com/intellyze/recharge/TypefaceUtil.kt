package com.intellyze.recharge
import android.content.Context
import android.graphics.Typeface
import android.util.Log

class TypefaceUtil {
    /**
     * Using reflection to override default typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT TYPEFACE WHICH WILL BE OVERRIDDEN
     * @param context to work with assets
     * @param defaultFontNameToOverride for example "monospace"
     * @param customFontFileNameInAssets file name of the font from assets
     */
    fun overrideFont(
        context: Context,
        defaultFontNameToOverride: String,
        customFontFileNameInAssets: String
    ) {
        try {
            val customFontTypeface =
                Typeface.createFromAsset(context.assets, customFontFileNameInAssets)
            val defaultFontTypefaceField =
                Typeface::class.java.getDeclaredField(defaultFontNameToOverride)
            defaultFontTypefaceField.isAccessible = true
            defaultFontTypefaceField[null] = customFontTypeface
        } catch (e: Exception) {
            Log.e(
                TypefaceUtil::class.java.simpleName,
                "$customFontFileNameInAssets instead of $defaultFontNameToOverride"
            )
        }
    }
}