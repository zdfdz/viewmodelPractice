package study.zdf.viewmodeldemo5_16;

import org.jetbrains.annotations.NotNull;

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/26 16:00
 */
public class CallBackTest {
    private ZDFCallBack callBack;

    public void init(@NotNull ZDFCallBack callBack) {
        this.callBack = callBack;
    }

    public void delay2000Respones(int sleepTimer) {
        if (callBack != null) {
            try {
                Thread.sleep(sleepTimer);
                callBack.baoGao(sleepTimer);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
