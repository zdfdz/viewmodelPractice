package study.zdf.common;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/26 11:35
 */
public class LiveDataBus {
    private static LiveDataBus liveDataBus = new LiveDataBus();
    private Map<String, MutableLiveData<Object>> map;

    private LiveDataBus() {
        map = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!map.containsKey(key)) {
            map.put(key, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) map.get(key);
    }
}
