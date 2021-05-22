package study.zdf.catactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import study.zdf.arouter.Arouter


class catActivity : AppCompatActivity() {

    private var btn_jumpMain: Button? = null
    private var btn_jumpSplash: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)
//        catAcyivityUtils()
        btn_jumpMain = findViewById(R.id.btn_jumpMain)
        btn_jumpSplash = findViewById(R.id.btn_jumpSplash)

        btn_jumpMain?.setOnClickListener {
            val targetActivity = Arouter.instance.getTargetActivity("main/main")
            startActivity(Intent(this@catActivity,targetActivity))
//            Arouter.instance.jump2Activity("main/main")
//            Toast.makeText(this, Arouter.instance.getNumber().toString(), Toast.LENGTH_SHORT).show()
        }
        btn_jumpSplash?.setOnClickListener {
            val targetActivity = Arouter.instance.getTargetActivity("splash/splash")
            startActivity(Intent(this@catActivity,targetActivity))
//            Arouter.instance.jump2Activity("splash/splash")
        }

    }
}
