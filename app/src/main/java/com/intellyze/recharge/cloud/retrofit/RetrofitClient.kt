package com.intellyze.recharge.cloud.retrofit
import android.content.Context
import androidx.databinding.library.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class RetrofitClient {

    companion object {

        var instance: Retrofit? = null
        var mContext: Context? = null
        var mUrl: String? = null
        fun getRetrofitClient(context: Context, baseUrl: String): Retrofit? {
            if (mContext == null) {
                mContext = context
                return getRetrofitClient(
                    baseUrl
                )
            } else {
                return instance as Retrofit;

            }
        }

        fun getRetrofitClient(baseUrl: String): Retrofit? {

            mUrl = baseUrl
//            val builder = OkHttpClient()()
//            builder.readTimeout(60, TimeUnit.SECONDS)
//            builder.connectTimeout(60, TimeUnit.SECONDS)

//            val client = OkHttpClient.Builder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS).build()
//
////            var client: OkHttpClient = getUnsafeOkHttpClient()
//                .build()


            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()




            instance = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .client(client)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(mUrl)
                .build()
            return instance

        }



    }
}
