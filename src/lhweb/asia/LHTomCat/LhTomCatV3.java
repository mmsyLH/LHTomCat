package lhweb.asia.LHTomCat;

import lhweb.asia.LHTomCat.thread.LhServerV3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 罗汉汤姆猫v3
 * nioserver v2
 * nioserver
 *
 * @author 罗汉
 * @date 2024/03/04
 */
public class LhTomCatV3 {
    public static void main(String[] args) {
        try {
            // 1.开启服务器通道  ServerSocketChannel比较特殊不实现读和写的接口 本身并不传入数据
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 2.设置为非阻塞 NIO
            serverSocketChannel.configureBlocking(false);
            // 3.监听端口
            serverSocketChannel.bind(new InetSocketAddress(10086));

            // 工具人：1、开启工具人  通道管理器(Selector)
            Selector selector = Selector.open();
            System.out.println("工具人已经上线");
            // 工具人：2、接受任务  帮助serverSocketChannel查看是否可以Accept
            /**
             * 将通道(Channel)注册到通道管理器(Selector)，并为该通道注册selectionKey.OP_ACCEPT事件
             * 注册该事件后，当事件到达的时候，selector.select()会返回，
             * 如果事件没有到达selector.select()会一直阻塞。
             */
            /**
             *第二个参数SelectionKey.OP_ACCEPT指定了要为通道注册的事件类型。在Java NIO中，
             * SelectionKey.OP_ACCEPT表示对"接受"事件感兴趣，即当有新的连接可以接受时，会触发该事件。
             *具体来说，OP_ACCEPT是一个表示接受连接就绪的操作集位，用于表示服务器套接字通道准备好接受新的客户端连接。
             * 当服务器套接字通道准备好接受新的连接时，会触发OP_ACCEPT事件。
             *通过将OP_ACCEPT作为第二个参数传递给register方法，
             * 告诉选择器(selector)对serverSocketChannel通道感兴趣的事件类型是"接受连接"事件。
             * 当这种事件发生时，selector.select()将会返回，从而允许你处理接受连接的逻辑。
             *总结：SelectionKey.OP_ACCEPT用于表示注册"接受连接"事件，以便在有新连接到达时能够及时处理。
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//
            LhServerV3 selectorThread = new LhServerV3(selector);
            selectorThread.start();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
