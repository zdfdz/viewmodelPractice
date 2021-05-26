package study.zdf.viewmodeldemo5_16

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import study.zdf.annotation.BindPath
import study.zdf.catactivity.catActivity

@BindPath(path = "main/main")
class MainActivity : AppCompatActivity() {
    private var viewModel: numberViewmodel? = null
    private var textView: TextView? = null
    private var btn_1: Button? = null
    private var btn_2: Button? = null
    private var btn_jumpCat: Button? = null
    private var btn_callback: Button? = null
    private val callBackTest: CallBackTest by lazy { CallBackTest() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mainAcyivityUtils()
        textView = this.findViewById(R.id.tv_number)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_jumpCat = findViewById(R.id.btn_jumpCat)
        btn_callback = findViewById(R.id.btn_callback)
        callBackTest.init(myListener(this@MainActivity))

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(numberViewmodel::class.java)
        viewModel?.getNumber()?.observe(this, Observer<Int> {
            textView?.text = it.toString()
        })

        btn_1?.setOnClickListener {
            viewModel?.setCountDownNumber(1)
        }
        btn_2?.setOnClickListener {
            viewModel?.setCountDownNumber(2)
        }
        btn_jumpCat?.setOnClickListener {
            val intent = Intent(this, catActivity::class.java)
            startActivity(intent)
        }
//        btn_callback?.setOnClickListener {
//            callBackTest.delay2000Respones(2000)
//        }

        btn_callback?.setOnClickListener {
            callBackTest.delay2000Respones(2000)
        }

    }

    class myListener(val context: Context) : ZDFCallBack {

        override fun baoGao(num: Int) {
            Toast.makeText(context, "delay $num ms print message", Toast.LENGTH_SHORT).show()
        }

    }

}


interface ZDFCallBack {
    // 定义一个报告 反馈的方法
    fun baoGao(num: Int)
}
