package cn.jm.happy.ipush

import android.app.Application
import cn.jpush.android.api.JPushInterface
import cn.jm.happy.ipush.vendor.Vendor

object IPushManager {
    private val tokenListener = mutableListOf<OnVendorTokenChangeCallback>()
    fun init(context: Application) {
        JPushInterface.setDebugMode(BuildConfig.DEBUG)
        JPushInterface.init(context)
    }

    fun registerTokenListener(listener: OnVendorTokenChangeCallback) {
        tokenListener.add(listener)
    }

    fun unRegisterTokenListener(listener: OnVendorTokenChangeCallback) {
        tokenListener.remove(listener)
    }

    internal fun onTokenChangeCallback(platform: Vendor, token: String) {
        tokenListener.forEach {
            it.onTokenChangeCallback(platform, token)
        }
    }

    interface OnVendorTokenChangeCallback {
        fun onTokenChangeCallback(platform: Vendor, token: String)
    }

}