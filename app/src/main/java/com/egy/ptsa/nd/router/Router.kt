package com.egy.ptsa.nd.router

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

object Router {
    fun <T> routerForClass(context: Context, clazz: Class<T>){
        context.startActivity(Intent(context, clazz))
    }

    fun <T> routerForClassAndData(context: AppCompatActivity, clazz: Class<T>, data: Map<String, String>){
        val intent = Intent(context, clazz)
        for (item in data){
            intent.putExtra(item.key, item.value)
        }

        context.startActivity(intent)
    }

    fun back(context: AppCompatActivity){
        context.finish()
    }
}