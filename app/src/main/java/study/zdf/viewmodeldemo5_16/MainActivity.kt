package study.zdf.viewmodeldemo5_16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private var viewModel: numberViewmodel? = null
    private var textView: TextView? = null
    private var btn_1: Button? = null
    private var btn_2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tv_number)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(numberViewmodel::class.java)
        viewModel?.getNumber()?.observe(this, Observer<Int> {
            textView?.text= it.toString()
        })

        btn_1?.setOnClickListener {
            viewModel?.setCountDownNumber(1)
        }
        btn_2?.setOnClickListener {
            viewModel?.setCountDownNumber(2)
        }


    }
}
