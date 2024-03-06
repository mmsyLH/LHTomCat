package lhweb.asia.LHTomCat;

import lhweb.asia.LHTomCat.thread.LhServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 罗汉服务器v1
 * 第一个版本的tomcat 可以完成接受浏览器发送的请求，并返回相关信息
 *
 * @author 罗汉
 * @date 2024/02/25
 */
public class LhTomcatV2 {

    public static void main(String[] args) throws IOException {
        // 1 创建ServletSocket 在8888端口监听
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("==========================lhTomcatV1在8888端口监听======================");
        // 如果没有关闭就一直在等待监听
        while (!serverSocket.isClosed()) {
            /**
             * 1 等待浏览器/客户端的连接如果有连接来，就创建一个socket
             * 2 这个socket就是服务端和浏览器/客户端之间的通道
             */
            Socket socket = serverSocket.accept();
            // 2 创建一个线程对象，并且吧socket传给线程
            new Thread(new LhServer(socket)).start();
        }
    }
}
