package study.zdf.viewmodeldemo5_16

import android.app.Application
import study.zdf.arouter.Arouter
import study.zdf.catactivity.catActivity
import study.zdf.twoactivity.splashActivity

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/21 22:48
 */
class myApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Arouter.instance.init(this)
        Arouter.instance.addActivity("main/main",MainActivity::class.java)
        Arouter.instance.addActivity("cat/cat",catActivity::class.java)
        Arouter.instance.addActivity("splash/splash",splashActivity::class.java)
    }
}