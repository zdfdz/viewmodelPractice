package study.zdf.viewmodeldemo5_16

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/16 16:28
 */
class numberViewmodel:ViewModel(){
    private var linkNumber = MutableLiveData<Int>()
    init {
        linkNumber.value = 0
    }
    fun setCountDownNumber(number:Int){
        linkNumber.value = linkNumber.value!! + number
    }
    fun getNumber(): MutableLiveData<Int> {
        return linkNumber
    }
}
