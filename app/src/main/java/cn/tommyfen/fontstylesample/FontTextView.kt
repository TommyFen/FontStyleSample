package cn.tommyfen.fontstylesample

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * @author : Tommy
 * @date : 2018/4/09
 * 自定义带指定字体样式的TextView，在项目界面布局中引用
 */

class FontTextView : AppCompatTextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setTypeface(tf: Typeface?, style: Int) {
        super.setTypeface(createTypeface(context, "fonts/digifaw.ttf"), style)
    }

    private fun createTypeface(context: Context, fontPath: String): Typeface {
        return Typeface.createFromAsset(context.assets, fontPath)
    }

}
