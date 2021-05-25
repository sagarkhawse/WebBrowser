package com.laboontech.webbrowser.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import com.kaopiz.kprogresshud.KProgressHUD
import org.json.JSONObject

class Singleton {
   // val imgURL ="http://attendance.aprosoftech.com/Images/"
    val prefName ="SportsPref"
    val userPref ="UserPref"

    fun setSharedPrefrence(context: Context, jsonObject: JSONObject){
        val sharedPreferences:SharedPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(userPref,jsonObject.toString()).apply()
    }

    fun getUserFromSharedPrefrence(context: Context):JSONObject?{
        val sharedPreferences:SharedPreferences=context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
        return if (sharedPreferences.contains(userPref)){
            val userString=sharedPreferences.getString(userPref,"")
            if (userString.equals("",true)){
                null
            }else{
                JSONObject(userString)
            }
        }else{
            null
        }
    }

    fun createLoading(context: Context?): KProgressHUD? {
        return KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel(title)
//                .setDetailsLabel(message)
                .setCancellable(false)
                .setAnimationSpeed(3)
                .setDimAmount(0.4f)
                .show()
    }


    fun shakeError(context: Context): TranslateAnimation?{
        val shake= TranslateAnimation(0F, 10F, 0F, 0F)
        shake.duration = 500
        shake.interpolator= CycleInterpolator(7F)

        return shake
    }

}
