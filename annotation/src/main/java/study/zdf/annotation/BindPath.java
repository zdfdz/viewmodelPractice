package study.zdf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZhengDeFeng
 * @description:
 * @date :2021/5/24 20:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BindPath {
    String path();
}
