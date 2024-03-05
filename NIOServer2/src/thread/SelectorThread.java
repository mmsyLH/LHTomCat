package thread;

import http.HttpRequest;
import http.HttpResponse;

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
public class SelectorThread extends Thread {
    private Selector selector;

    public SelectorThread(Selector selector) {
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
                        System.out.println("工具人查看到，可以Accept");
                        SocketChannel socketChannel = channel.accept();
                        System.out.println("");
                        // 设置为非阻塞
                        socketChannel.configureBlocking(false);
                        // 再注册
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("工具人已经注册");
                    } else if (selectionKey.isReadable()) {// 是否 可以读
                        // 获取通道selectionKey
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        HttpRequest httpRequest = new HttpRequest(socketChannel);
                        HttpResponse httpResponse = new HttpResponse(socketChannel);
                        String uri = httpRequest.getUri();
                        File file = new File("12306/webapps" + uri);
                        if (file.exists()) {
                            httpResponse.write(file);
                        }
                        socketChannel.close();
                        System.out.println("工具人已经下线");
                    } else if (selectionKey.isWritable()) {// 是否 可以写
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("工具人查看到可以写");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
