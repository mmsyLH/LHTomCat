package lhweb.asia.LHTomCat;

import lhweb.asia.LHTomCat.thread.LhServer;
import lhweb.asia.LHTomCat.servlet.UserServlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 罗汉服务器v1
 * 第一个版本的tomcat 可以完成接受浏览器发送的请求，并返回相关信息
 *
 * @author 罗汉
 * @date 2024/02/25
 */
public class LhTomcatV1 {
    // 定义属性 保存要扫描的包的全路径
    private static List<String> classFullPathList = new ArrayList<>();
    // 定义属性 servlet容器 存放反射后生成的bean对象  比如controller service  目前放入的都是单例的 多例一般是动态生成
    public static Map<String, Object> servletMap = new ConcurrentHashMap<>();
    // 定义属性 要扫描的xml文件
    private static String coonfigLocation;
    public static void main(String[] args) throws IOException {
        // 1 创建sevlet路径 todo 目前直接写 之后可以通过xml配置或者注解配置
        servletMap.put("UserServlet",new UserServlet());


        // 2 创建ServletSocket 在8888端口监听
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("==========================lhTomcatV1在8888端口监听======================");
        // 如果没有关闭就一直在等待监听
        while (!serverSocket.isClosed()) {
            /**
             * 1 等待浏览器/客户端的连接如果有连接来，就创建一个socket
             * 2 这个socket就是服务端和浏览器/客户端之间的通道
             */
            Socket socket = serverSocket.accept();
            // 3 创建一个线程对象，并且吧socket传给线程
            new Thread(new LhServer(socket)).start();
        }
    }
}
