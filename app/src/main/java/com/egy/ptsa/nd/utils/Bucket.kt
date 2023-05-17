package com.egy.ptsa.nd.utils

import android.app.Application
import android.content.Context

object Bucket {
    var startUrl = ""
        set(value) {
            field = value
            PrefEgyptSand.saveStartUrl(value)
        }
        get() {
            field = PrefEgyptSand.getStartUrl()
            return field
        }
    var lastUrl = ""
        set(value) {
            field = value
            PrefEgyptSand.saveLastUrl(value)
        }
        get() {
            field = PrefEgyptSand.getLastUrl()
            return field
        }
    var status = ""
        set(value) {
            field = value
            PrefEgyptSand.saveStatus(value)
        }
        get() {
            field = PrefEgyptSand.getStatus()
            return field
        }
    var campaign = ""
        set(value) {
            field = value
            PrefEgyptSand.saveCampaign(value)
        }
        get() {
            field = PrefEgyptSand.getCampaign()
            return field
        }
    var fbclid = ""
        set(value) {
            field = value
            PrefEgyptSand.saveFbclid(value)
        }
        get() {
            field = PrefEgyptSand.getFbclid()
            return field
        }
    var pixelId = ""
        set(value) {
            field = value
            PrefEgyptSand.savePixelId(value)
        }
        get() {
            field = PrefEgyptSand.getPixelId()
            return field
        }
    var isMusic = false
        set(value) {
            field = value
            PrefEgyptSand.saveMusic(value)
        }
        get() {
            field = PrefEgyptSand.isMusic()
            return field
        }

    suspend fun init(context: Application) {
        PrefEgyptSand.initPref(context)
    }
}