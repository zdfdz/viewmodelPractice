package study.zdf.catactivity

import study.zdf.arouter.Arouter
import study.zdf.arouter.IRouter


/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/21 22:33
 */
class catAcyivityUtils: IRouter {
    override fun putActivity() {
        Arouter.instance.addActivity("cat/cat",catActivity::class.java)
    }

    init {
        putActivity()
    }

}