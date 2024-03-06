package lhweb.asia.LHTomCat.thread;

import lhweb.asia.LHTomCat.http.LhRequestV3;
import lhweb.asia.LHTomCat.http.LhResponseV3;
import lhweb.asia.LHTomCat.http.LhServletV3;
import lhweb.asia.LHTomCat.utils.ServletFactoryV3;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author :罗汉
 * @date : 2024/3/5
 */
public class LhServerV3 extends Thread {
    private Selector selector;

    public LhServerV3(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        // 工具人：3、一直工作： 查看哪一路有数据[Accept,Rebad Writer]
        while (true) {// 封装成线程
            try {
                selector.select();// 检测到是否可读
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {// 是否 可以接受Accept
                        // 获取通道  selectionKey.channel()返回值是所有通道的父类
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("--------------------------------");
                        System.out.println("工具人查看到，可以Accept");
                        SocketChannel socketChannel = channel.accept();
                        // 设置为非阻塞
                        socketChannel.configureBlocking(false);
                        // 再注册
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("工具人已经注册");
                    } else if (selectionKey.isReadable()) {// 是否 可以读
                        System.out.println("工具人通知可以读");
                        // 获取通道selectionKey
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        LhRequestV3 lhRequest = new LhRequestV3(socketChannel);
                        LhResponseV3 lhResponse = new LhResponseV3(socketChannel);

                        //业务逻辑
                        // 业务逻辑~  判断是走静态资源还是访问serverLet
                        // 判断走serverLet uri:/LhTomCat/UserServlet  url:/LhTomCat/UserServlet?username=admina&password=123456
                        String serverName = "";
                        String serverLetName = "";
                        System.out.println("uri:" + lhRequest.getUri());
                        System.out.println("url:" + lhRequest.getUrl());
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
                            LhServletV3 lhServlet = ServletFactoryV3.getInstance().getServlet(serverLetName);
                            if (lhServlet != null) {
                                lhServlet.service(lhRequest, lhResponse);
                                break;
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
                                break;
                            } else {
                                file = new File("12306/webapps/404.html");
                                lhResponse.write(file);
                                break;
                            }
                        }


                        socketChannel.close();
                        System.out.println("任务完成");
                    } else if (selectionKey.isWritable()) {// 是否 可以写
                        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("工具人查看到可以写");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
