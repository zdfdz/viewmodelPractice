package study.zdf.arouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.util.HashMap

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/22 13:49
 */
class Arouter {
    private val i = 100
    private var context: Context? = null
    private val clazzMap = HashMap<String, Class<out Activity>>()

    companion object {
        val instance: Arouter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Arouter()
        }
    }

    fun init(context: Context) {
        this.context = context
    }

    fun getNumber(): Int {
        return i
    }

    fun addActivity(key: String, value: Class<out Activity>) {
        if (key != null && value != null && !clazzMap.containsKey(key)) {
            clazzMap[key] = value
        }
    }

    fun jump2Activity(key: String) {
        val targetActivity = clazzMap[key]
        if (targetActivity != null) {
            context?.startActivity(Intent(context, targetActivity))
        }
    }

    fun getTargetActivity(key: String): Class<out Activity>? {
        return clazzMap[key]
    }
}