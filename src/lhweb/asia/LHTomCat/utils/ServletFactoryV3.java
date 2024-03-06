package lhweb.asia.LHTomCat.utils;

import lhweb.asia.LHTomCat.http.LhHttpServlet;
import lhweb.asia.LHTomCat.http.LhHttpServletV3;
import lhweb.asia.LHTomCat.servlet.TrainServletV3;
import lhweb.asia.LHTomCat.servlet.UserServletV3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :罗汉
 * @date : 2024/3/3
 */
public class ServletFactoryV3 {
    // 定义属性 servlet容器 存放反射后生成的bean对象  比如controller service  目前放入的都是单例的 多例一般是动态生成
    public static Map<String, LhHttpServletV3> servletMap = new ConcurrentHashMap<>();
    private static final ServletFactoryV3 instance=new ServletFactoryV3();

    private ServletFactoryV3() {
        servletMap.put("UserServlet",new UserServletV3());
        servletMap.put("TrainServlet",new TrainServletV3());
    }

    /**
     * 获得实例
     *
     * @return {@link ServletFactoryV3}
     */
    public static ServletFactoryV3 getInstance() {
        return instance;
    }

    /**
     * 得到servlet
     *
     * @param url url
     * @return {@link LhHttpServlet}
     */
    public LhHttpServletV3 getServlet(String url) {
        return servletMap.get(url);
    }
}
