package lhweb.asia.LHTomCat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * web servlet的注解
 *
 * @author 罗汉
 * @date 2024/03/06
 */
@Retention(RetentionPolicy.RUNTIME)//运行的时候渲染
@Target(value = {ElementType.TYPE})//TYPE 类的头上
public @interface WebServlet {
    String value() default "/";
    int id() default 0;
}
