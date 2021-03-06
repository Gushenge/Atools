package com.gushenge.atools

import android.app.Application
import com.gushenge.atools.util.APreference

object ATools {

    lateinit var context:Application
    fun init(context: Application):ATools{
        this.context = context
        return this
    }

    fun preference(sharedPreferencesName: String) {
        APreference.init(context, sharedPreferencesName)
    }
}