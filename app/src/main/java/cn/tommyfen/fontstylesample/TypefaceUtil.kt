package cn.tommyfen.fontstylesample

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.Hashtable


/**
 * @author : Tommy
 * 递归批量替换某个 View 及其子 View 的字体 工具类
 * 全局替换TextView 及其子类的文字样式
 */

object TypefaceUtil {

    private const val TAG = "TypefaceUtil"

    /**
     * 缓存机制，有效缓解卡顿
     */
    private val cache = Hashtable<String, Typeface>()

    /**
     * 在 Activity 下替换其根节点下所有的 指定 View 的字体
     *
     * @param context  Activity 对象
     * @param fontPath 字体路径
     */
    fun replaceFont(context: Activity, fontPath: String) {
        replaceFont(getRootView(context), fontPath)
    }


    /**
     * 替换文字样式
     *
     * @param root     View
     * @param fontPath 字体 ttf 文件路径
     */
     fun replaceFont(root: View, fontPath: String) {

        if (TextUtils.isEmpty(fontPath)) return

        if (root is TextView) {
            //如果当前类型是 TextView 或其子类，则替换其字体
            val textView = root as TextView?
            var style = Typeface.NORMAL
            if (textView!!.typeface != null) {
                style = textView.typeface.style
            }
            textView.setTypeface(createTypeface(root.context, fontPath), style)
        } else if (root is ViewGroup) {
            //如果是 ViewGroup 则遍历其下面的子 View, 然后执行本方法（递归）
            val viewGroup = root as ViewGroup?
            for (i in 0 until viewGroup!!.childCount) {
                replaceFont(viewGroup.getChildAt(i), fontPath)
            }
        }
    }

    /**
     * 从 Activity 中获取 rootView 的根节点
     *
     * @param context Activity 对象
     * @return 当前 Activity 布局的根节点
     */
    private fun getRootView(context: Activity): View {
        return (context.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    private fun createTypeface(c: Context, assetPath: String): Typeface? {
        synchronized(cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    val t = Typeface.createFromAsset(c.assets, assetPath)
                    cache[assetPath] = t
                } catch (e: Exception) {
                    Log.e(TAG, "Could not get typeface '" + assetPath + "' because " + e.message )
                    return null
                }

            }
            return cache[assetPath]
        }
    }

}
