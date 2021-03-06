package com.gushenge.atools.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.gushenge.atools.R


class ArcButton : TextView {

    //默认的圆角半径
    private var radius = 1000000000f

    //默认文字和背景颜色
    private var mBgNormalColor = Color.GRAY
    private var mBgPressedColor = mBgNormalColor
    private var mTextNormalColor = Color.BLACK
    private var mTextPressedColor = mTextNormalColor

    constructor(context: Context):this(context,null,0)
    constructor(context: Context,attributes: AttributeSet):this(context,attributes,0)
    constructor(context: Context, attributes: AttributeSet?, defStyle:Int):super(context,attributes,defStyle){
        val typeArray = context.obtainStyledAttributes(attributes, R.styleable.ArcButton)
        radius = typeArray.getDimension(R.styleable.ArcButton_radius,10000000f)
        mBgNormalColor = typeArray.getColor(R.styleable.ArcButton_bgColor,Color.GRAY)
        mBgPressedColor = typeArray.getColor(R.styleable.ArcButton_pressedBgColor,mBgNormalColor)
        mTextNormalColor = typeArray.getColor(R.styleable.ArcButton_textColor,Color.BLACK)
        mTextPressedColor = typeArray.getColor(R.styleable.ArcButton_pressedTextColor,mTextNormalColor)
        initUI()
    }

    private fun initUI() {
        gravity = Gravity.CENTER
        buildDraweableState()
        buildColorDrawableState()
    }

    /**
     * 构建图片drawble
     */
    private fun buildColorDrawableState() {
        val colorStateList = ColorStateList(
            arrayOf(mPressState, mNormalState),
            intArrayOf(mTextPressedColor, mTextNormalColor)
        )
        setTextColor(colorStateList)
    }

    /**
     * 构建背景Drawble
     */
    private fun buildDraweableState() {

        val outRectr = floatArrayOf(
            radius,
            radius,
            radius,
            radius,
            radius,
            radius,
            radius,
            radius
        )
        //创建状态管理器
        val drawable = StateListDrawable()
        /**
         * 注意StateListDrawable的构造方法我们这里使用的
         * 是第一参数它是一个float的数组保存的是圆角的半径，它是按照top-left顺时针保存的八个值
         */
        //创建圆弧形状
        val rectShape = RoundRectShape(outRectr, null, null)
        //创建drawable
        val pressedDrawable = ShapeDrawable(rectShape)
        //设置我们按钮背景的颜色
        pressedDrawable.paint.color = mBgPressedColor
        //添加到状态管理里面
        drawable.addState(mPressState, pressedDrawable)

        val normalDrawable = ShapeDrawable(rectShape)
        normalDrawable.paint.color = mBgNormalColor
        drawable.addState(mNormalState, normalDrawable)
        //设置我们的背景，就是xml里面的selector
        background = drawable
    }

    /**
     * 设置圆角矩形
     *
     * @param radius
     */
    fun setRadius(radius: Float) {
        this.radius = radius
        buildDraweableState()
    }
    /**
     * 设置按钮背景颜色
     *
     * @param normalColor
     * @param prssedClor
     */
    fun setBackgroundColor(normalColor: Int, pressedColor: Int) {

        mBgNormalColor = normalColor
        mBgPressedColor = pressedColor
        buildDraweableState()

    }

    /**
     * 设置按钮文字颜色
     *
     * @param normalColor
     * @param pressedColor
     */
    fun setTextColor(normalColor: Int, pressedColor: Int) {
        mTextPressedColor = pressedColor
        mTextNormalColor = normalColor
        buildColorDrawableState()

    }

    /**
     * 设置按钮背景颜色
     *
     * @param color
     */
    override fun setBackgroundColor(color: Int) {

        mBgNormalColor = color
        mBgPressedColor = color
        buildDraweableState()

    }

    /**
     * 设置按钮文字颜色
     *
     * @param color
     */
    override fun setTextColor(color: Int) {
        mTextPressedColor = color
        mTextNormalColor = color
        buildColorDrawableState()

    }
    companion object {
        var mNormalState = intArrayOf()
        var mPressState = intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled)
    }
}