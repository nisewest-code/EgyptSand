package com.egy.ptsa.nd.lib

import android.content.Context
import com.egy.ptsa.nd.str.Ids
import com.egy.ptsa.nd.str.Linked
import com.egy.ptsa.nd.str.StrHelp
import com.egy.ptsa.nd.utils.Bucket
import com.egy.ptsa.nd.utils.PrefEgyptSand
import com.egy.ptsa.nd.utils.network.Analytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object FireLib {
    fun init(context: Context, callbackSuccess: (link: String)->Unit, callbackFail: ()->Unit){
        val db = Firebase.firestore
        val docRef = db.collection("domain").document(StrHelp.getIdFire(context))
        docRef.get()
            .addOnSuccessListener { document ->
                Analytics.backendReceivedTime()
                // Если имя компании и диплинк не пришли
                val nameRef = "url"
                if (document != null) {
                    var urlFirebase = document.getString(nameRef)
                    if (urlFirebase != null) {
                        if (urlFirebase.isEmpty()) {
                            callbackFail()
                        } else {
                            Analytics.backendUrl(urlFirebase)
                            urlFirebase = if (Linked.link.isNotEmpty()) {
                                StrHelp.getNonOrganic(context, Linked.link, urlFirebase, Linked.advertisingId, Ids.appsId())
                            } else {
                                StrHelp.getOrganic(context, urlFirebase, Linked.advertisingId, Ids.appsId())

                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                Bucket.startUrl = urlFirebase
                            }
                            Analytics.logFirstUrl(urlFirebase)
                            callbackSuccess(urlFirebase)
                        }
                    } else {
                        callbackFail()
                    }
                } else {
                    callbackFail()
                }
            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
                callbackFail()
            }
    }
}