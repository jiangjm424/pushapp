package cn.jm.happy.pushapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.jm.happy.ipush.IPushManager
import cn.jm.happy.ipush.vendor.Vendor

class MainActivity : AppCompatActivity(), IPushManager.OnVendorTokenChangeCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IPushManager.init(application)
        IPushManager.registerTokenListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        IPushManager.unRegisterTokenListener(this)
    }

    override fun onTokenChangeCallback(platform: Vendor, token: String) {
        Log.i("MainActivity", "token:$token")
    }
}