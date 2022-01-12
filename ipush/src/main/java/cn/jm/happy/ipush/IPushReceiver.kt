package cn.jm.happy.ipush

import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.CmdMessage
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver
import cn.jm.happy.ipush.vendor.Vendor


class IPushReceiver : JPushMessageReceiver() {

    companion object {
        private const val TAG = "IPushReceiver"
    }
    override fun onMessage(context: Context?, message: CustomMessage?) {
        super.onMessage(context, message)
        Log.v(TAG, "on message:$message")
    }

    override fun onCommandResult(context: Context?, cmdMessage: CmdMessage?) {
        Log.v(TAG, "jpush cmd:${cmdMessage?.cmd}, msg:${cmdMessage}")
        cmdMessage?.takeIf { msg ->
            msg.cmd == 10000 && msg.extra != null
        }?.let {
            val token = it.extra.getString("token")
            val platform = it.extra.getInt("platform")
            token?.also { t ->
                IPushManager.onTokenChangeCallback(platform = Vendor.mapVendor(platform), t)
            }
        }
        super.onCommandResult(context, cmdMessage)
    }

    override fun onConnected(context: Context?, connected: Boolean) {
        super.onConnected(context, connected)
        Log.v(TAG, "jpush on connected:$connected")
    }

    override fun onNotifyMessageArrived(
        context: Context?,
        notificationMessage: NotificationMessage?
    ) {
        super.onNotifyMessageArrived(context, notificationMessage)
        Log.v(TAG, "arrived message $notificationMessage")
    }

    /**
     * 点击通知方案1
     * 点击 推送过来的消息。
     * 协议消息会带上额外信息字段，里面有一个jump字段并且jump字段配置的目的页面是在本应用中已经开发好的，按arouter协议跳转即可
     * eg:{"jump":"aga://bb.com/app/web?title=aaa&jump=aHR0cHM6Ly9jbi5iaW5nLmNvbS8="}
     *
     * 点击通知方案2(建议)
     * 直接在消息中加入参数，点击后打开指定的页面即可，此时不会调用到此回调好像。直接打开了参数中对应的activity
     * @param context Context
     * @param notificationMessage NotificationMessage
     */
    override fun onNotifyMessageOpened(
        context: Context?,
        notificationMessage: NotificationMessage?
    ) {
        Log.v(TAG, "open message $notificationMessage")
//        notificationMessage?.let { message ->
//            val url = Gson().fromJson<Map<String, String>>(message.notificationExtras)
//            url.forEach {
//                Log.v(TAG, "key:${it.key} , value:${it.value}")
//            }
//            url["jump"]?.let {
//                ARouter.getInstance().buildWithParameters(it).navigation()
//            } ?: null
//        } ?: super.onNotifyMessageOpened(context, notificationMessage)
        super.onNotifyMessageOpened(context, notificationMessage)
    }

    override fun onMultiActionClicked(context: Context?, intent: Intent?) {
        super.onMultiActionClicked(context, intent)
    }
}
