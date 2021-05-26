package study.zdf.twoactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import study.zdf.annotation.BindPath
import study.zdf.arouter.Arouter

@BindPath(path = "splash/splash")
class splashActivity : AppCompatActivity() {

    private var btn_jumpMain_2: Button? = null
    private var btn_jumpSplash_2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        splashAcyivityUtils()

        btn_jumpMain_2 = findViewById(R.id.btn_jumpMain_2)
        btn_jumpSplash_2 = findViewById(R.id.btn_jumpSplash_2)

        btn_jumpMain_2?.setOnClickListener {
            val targetActivity = Arouter.instance.getTargetActivity("main/main")
            startActivity(Intent(this@splashActivity,targetActivity))
//            Arouter.instance.jump2Activity("main/main")
//            Toast.makeText(this, Arouter.instance.getNumber().toString(), Toast.LENGTH_SHORT).show()
        }
        btn_jumpSplash_2?.setOnClickListener {
            val targetActivity = Arouter.instance.getTargetActivity("cat/cat")
            startActivity(Intent(this@splashActivity,targetActivity))
//            Arouter.instance.jump2Activity("splash/splash")
        }
    }
}
