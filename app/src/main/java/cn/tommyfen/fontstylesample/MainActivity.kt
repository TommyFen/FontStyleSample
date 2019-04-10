package cn.tommyfen.fontstylesample

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val FONT_PATH = "fonts/digifaw.ttf"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        normal_change.setOnClickListener {
            val manager = assets
            tv_normal_change.typeface = Typeface.createFromAsset(manager, FONT_PATH)
        }

        all_change.setOnClickListener {
            TypefaceUtil.replaceFont(ll_views, FONT_PATH)
        }

    }
}
