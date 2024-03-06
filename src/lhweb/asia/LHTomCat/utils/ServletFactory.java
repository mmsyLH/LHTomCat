package lhweb.asia.LHTomCat.utils;

import lhweb.asia.LHTomCat.http.LhHttpServlet;
import lhweb.asia.LHTomCat.http.LhHttpServletV3;
import lhweb.asia.LHTomCat.servlet.TrainServlet;
import lhweb.asia.LHTomCat.servlet.UserServlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :罗汉
 * @date : 2024/3/3
 */
public class ServletFactory {
    // 定义属性 servlet容器 存放反射后生成的bean对象  比如controller service  目前放入的都是单例的 多例一般是动态生成
    public static Map<String, LhHttpServlet> servletMap = new ConcurrentHashMap<>();
    private static final ServletFactory instance=new ServletFactory();

    private ServletFactory() {
        servletMap.put("UserServlet",new UserServlet());
        servletMap.put("TrainServlet",new TrainServlet());
    }

    /**
     * 获得实例
     *
     * @return {@link ServletFactory}
     */
    public static ServletFactory getInstance() {
        return instance;
    }

    /**
     * 得到servlet
     *
     * @param url url
     * @return {@link LhHttpServletV3}
     */
    public LhHttpServlet getServlet(String url) {
        return servletMap.get(url);
    }
}
