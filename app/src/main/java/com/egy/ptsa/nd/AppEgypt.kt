package com.egy.ptsa.nd

import android.app.Application
import android.content.Intent
import com.bugsee.library.Bugsee
import com.egy.ptsa.nd.lib.OneLib
import com.egy.ptsa.nd.str.Ids
import com.egy.ptsa.nd.utils.Bucket
import com.egy.ptsa.nd.utils.DeviceUtil
import com.egy.ptsa.nd.utils.SoundService
import com.egy.ptsa.nd.utils.network.Analytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppEgypt: Application() {

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            Bucket.init(this@AppEgypt)
        }
        Analytics.init(this)

//        Bugsee.launch(this, Ids.bugId())

        DeviceUtil.checkDevice(
            this, callbackFail = {},
            callbackSuccess = {
                OneLib.init(this)
            }
        )

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
    }
}