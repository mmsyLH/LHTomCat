package lhweb.asia.LHTomCat.test;

import lhweb.asia.LHTomCat.model.TrainAdmin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author :罗汉
 * @date : 2024/3/6
 */
public class ClassTest {
    public static void main(String[] args) {
        TrainAdmin trainAdmin = new TrainAdmin();
        try {
            //创建一个类对象：构造函数 方法 属性
            Class<?> aClass = Class.forName("lhweb.asia.LHTomCat.model.TrainAdmin");
            //1.构造函数
            Constructor<?>[] constructors = aClass.getConstructors();

            //2.属性
            Field[] declaredFields = aClass.getDeclaredFields();

            //3.方法
            Method[] declaredMethods = aClass.getDeclaredMethods();

            //4.注解
            Annotation[] annotations = aClass.getAnnotations();

            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
