package com.intellyze.recharge.ui.fragment


import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.intellyze.recharge.R
import com.intellyze.recharge.databinding.FragmentSplashBinding
import com.intellyze.recharge.utls.AppSharedPreferance
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash, container, false
        ) as FragmentSplashBinding
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activity?.actionBar?.hide()
        performNavigation()

    }

    fun performNavigation() {
        var pref = AppSharedPreferance(context)
        var deviceid = UUID.randomUUID().toString()
        pref.setStringPrefValue(AppSharedPreferance.Constants.MY_PHONE_LONG_ID, deviceid)
        if (pref.getStringPrefValue(AppSharedPreferance.Constants.SHOP_ID) == null) {
            findNavController().navigate(R.id.action_to_loginFragment)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

    }

    fun getAppBaseVersion(ctx: Context?): String? {
        var pInfo: PackageInfo? = null
        var version: String? = null
        try {
            if (ctx != null) {
                pInfo = ctx.packageManager.getPackageInfo(ctx.packageName, 0)
            }
            version = "" + (pInfo?.versionName ?: "0.0.0")

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return version
    }

}
