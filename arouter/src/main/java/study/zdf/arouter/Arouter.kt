package study.zdf.arouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import dalvik.system.DexFile
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
        val className = getClassName("study.zdf.utils")
        className.forEach {
            try {
                val aClazz = Class.forName(it)
                if (IRouter::class.java.isAssignableFrom(aClazz)) {
                    val iRouter = aClazz.newInstance() as IRouter
                    iRouter.putActivity()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun getClassName(packageName: String): List<String> {
        val classList = ArrayList<String>()
        try {
            val df = DexFile(context?.packageCodePath)
            val entries = df.entries()
            while (entries.hasMoreElements()) {
                val className = entries.nextElement()
                if (className.contains(packageName)) {
                    classList.add(className)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return classList
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