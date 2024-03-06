package lhweb.asia.LHTomCat.thread;

import lhweb.asia.LHTomCat.factory.ServletFactoryV3;
import lhweb.asia.LHTomCat.http.LhRequest;
import lhweb.asia.LHTomCat.http.LhResponse;
import lhweb.asia.LHTomCat.http.LhServlet;

import java.io.File;
import java.nio.channels.SocketChannel;

/**
 * 任务线程
 *
 * @author 罗汉
 * @date 2024/03/06
 */
public class TaskThread  extends Thread {
    private LhRequest lhRequest;
    private LhResponse lhResponse;
    private SocketChannel socketChannel;

    public TaskThread(LhRequest httpRequest, LhResponse httpResponse, SocketChannel socketChannel) {
        this.lhRequest = httpRequest;
        this.lhResponse = httpResponse;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            //业务逻辑
            // 业务逻辑~  判断是走静态资源还是访问serverLet
            // 判断走serverLet uri:/LhTomCat/UserServlet  url:/LhTomCat/UserServlet?username=admina&password=123456
            String serverName = "";
            String serverLetName = "";
            // System.out.println("uri:" + lhRequest.getUri());
            // System.out.println("url:" + lhRequest.getUrl());
            //切割servlet名称
            if (null != lhRequest.getUri() && !"/".equals(lhRequest.getUri())) {
                System.out.println("lhRequest.getUri()" + lhRequest.getUri());// /views/LhTomCat/TrainServlet
                int indexOf = lhRequest.getUri().lastIndexOf("LhTomCat");//如果k的值不存在，则返回-1 。
                if (indexOf>=0){
                    //获取服务器名称
                    serverName = "LhTomCat";
                    String substringUrl = lhRequest.getUri().substring(indexOf);
                    //获取setvlet名称
                    serverLetName = substringUrl.split("/")[1];
                }
            }
            // 一 走servlet
            if (lhRequest.getUri().split("/").length > 2 && "LhTomCat".equals(serverName)) {// todo servlet映射 这里是写死的方法 之后写活 例如xml+反射机制
                LhServlet lhServlet = ServletFactoryV3.getInstance().getServlet(serverLetName);
                if (lhServlet != null) {
                    lhServlet.service(lhRequest, lhResponse);
                    return;
                }
            } else {
                // 二 走静态资源
                // 1 判断是否是“/”访问首页 如果是则跳转到首页
                String fileName = "/index.html";
                if ("/".equals(lhRequest.getUri())) {
                    fileName = "12306/webapps/index.html";
                } else {
                    fileName = "12306/webapps" + lhRequest.getUri();
                }
                // 2 访问静态资源
                File file = new File(fileName);
                if (file.exists()) {
                    lhResponse.write(file);
                    return;
                } else {
                    file = new File("12306/webapps/404.html");
                    lhResponse.write(file);
                    return;
                }
            }
            socketChannel.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("任务完成");
        // String uri = httpRequest.getUri();
        // File file = new File("12306/webapps" + uri);
        // if (file.exists()) {
        //     httpResponse.write(file);
        // }
        // try {
        //     socketChannel.close();
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }
}
