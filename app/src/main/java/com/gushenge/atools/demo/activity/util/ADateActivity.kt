package com.gushenge.atools.demo.activity.util

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import com.gushenge.atools.demo.activity.BaseActivity
import com.gushenge.atools.demo.ui.titlebar
import com.gushenge.atools.ui.arcButton
import com.gushenge.atools.util.ADate
import com.gushenge.atools.util.ARandom
import com.gushenge.atools.util.AView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.ParseException

class ADateActivity : BaseActivity() {


    lateinit var time:EditText
    lateinit var stamp:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        verticalLayout {
            titlebar("ADate演示").init(viewManager = this,activity = this@ADateActivity)
            linearLayout {

                textView("时间戳"){
                    gravity = Gravity.CENTER
                    textColor = Color.BLACK
                }.lparams{
                    width = dip(0)
                    height = matchParent
                    weight = 1.toFloat()
                }
                textView("转换"){
                    gravity = Gravity.CENTER
                    textColor = Color.BLACK
                }.lparams{
                    width = dip(0)
                    height = matchParent
                    weight = 1.toFloat()
                }
                textView("时间"){
                    gravity = Gravity.CENTER
                    textColor = Color.BLACK
                }.lparams{
                    width = dip(0)
                    height = matchParent
                    weight = 1.toFloat()
                }
            }.lparams{
                width = matchParent
                height = dip(45)
            }

            linearLayout {
                stamp = editText {
                    hint = "时间戳"
                    gravity = Gravity.CENTER
                    inputType = InputType.TYPE_CLASS_NUMBER
                    maxEms = 10
                }.lparams{
                    width = 0
                    height = matchParent
                    weight =2.toFloat()
                }
                arcButton {
                    text = "转换"
                    onClick {
                        if(stamp.text.isNotEmpty()&&time.text.isNotEmpty()){
                            time.setText(ADate.getDateToSecond())
                            stamp.setText(ADate.getStamp().toString())
                        }else{
                            if (stamp.text.isNotEmpty()){
                                time.setText(ADate.stampToDate(stamp.text.trim().toString().toLong()))
                            }else if (time.text.isNotEmpty()){
                                try {
                                    stamp.setText(ADate.dateToStamp(time.text.trim().toString()).toString())
                                }catch (e:ParseException){
                                    toast("时间格式不对")
                                }
                            }else{
                                time.setText(ADate.getDateToSecond())
                                stamp.setText(ADate.getStamp().toString())
                            }
                        }
                    }
                }.lparams{
                    width = 0
                    height = dip(45)
                    weight =1.toFloat()
                }
                time = editText {
                    hint ="2019-06-14 18:00:00"
                    gravity = Gravity.CENTER
                }.lparams{
                    width = 0
                    height = matchParent
                    weight =2.toFloat()
                }
            }.lparams{

                width = matchParent
                height = dip(100)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "stampToDate()-时间戳转换为日期"
                onClick {
                    if(stamp.text.isNotEmpty()){
                        time.setText(ADate.stampToDate(stamp.text.toString().toLong()))
                    }else{
                        toast("时间戳为空")
                    }
                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "dateToStamp()-日期转换为时间戳"
                onClick {
                    if(time.text.isNotEmpty()){
                        stamp.setText(ADate.dateToStamp(time.text.toString()).toString())
                    }else{
                        toast("日期为空")
                    }
                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "getDateToSecond()-获取当前日期(精确到秒)"
                onClick {
                   time.setText(ADate.getDateToSecond())

                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "getDate()-获取当前日期(精确到毫秒)"
                onClick {
                   time.setText(ADate.getDate())

                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "getStamp()-获取当前10位时间戳"
                onClick {
                    stamp.setText(ADate.getStamp().toString())
                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }
            arcButton {
                val color = ARandom.color()
                textColor = if (AView.isLightColor(color)) Color.BLACK else Color.WHITE
                backgroundColor = color
                allCaps = false
                text = "getStampAs13()-获取当前13位时间戳"
                onClick {
                    stamp.setText(ADate.getStampAs13().toString())
                }
            }.lparams{
                width = matchParent
                height = dip(45)
                margin = dip(5)
            }

        }
    }
}
