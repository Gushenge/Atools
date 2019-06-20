package com.gushenge.atools.demo.activity.util

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gushenge.atools.demo.activity.BaseActivity
import com.gushenge.atools.demo.ui.titlebar
import com.gushenge.atools.ui.arcButton
import com.gushenge.atools.util.ARandom
import com.gushenge.atools.util.ASystem
import com.gushenge.atools.util.AView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ASystemActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            titlebar("ASystem").init(viewManager = this,activity = this@ASystemActivity)
            arcButton("getAppVersionCode()") {
                var color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                textSize = sp(6).toFloat()
                onClick {
                    val code = ASystem.getAppVersionCode(this@ASystemActivity)
                    toast("当前APP VersionCode为$code ")
                }
            }.lparams(width = matchParent,height = dip(40)){
                margin = dip(5)
            }
        }
    }
}
