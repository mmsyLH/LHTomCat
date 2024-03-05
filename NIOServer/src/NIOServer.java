import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nioserver
 *
 * @author 罗汉
 * @date 2024/03/04
 */
public class NIOServer {
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


            // 工具人：3、一直工作： 查看哪一路有数据[Accept,Rebad Writer]
            while (true) {//封装成线程
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
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);// 封装了        byte[] bytes = new byte[1024];
                        int count = channel.read(byteBuffer);
                        if (count > 0) {
                            byte[] buffer = byteBuffer.array();
                            String str = new String(buffer, 0, count);
                            System.out.println("str=" + str);

                            // 解析 响应
                            StringBuilder stringBuilder = new StringBuilder(1024);
                            stringBuilder.append("HTTP/1.1 200 OK" + "\r\n");
                            stringBuilder.append("Content-Type: text/html; charset=UTF-8" + "\r\n");
                            stringBuilder.append("Content-Length: " + str.getBytes().length + "\r\n");
                            stringBuilder.append("\r\n");
                            String response="haha";
                            stringBuilder.append(response);
                            byte[] bytes = stringBuilder.toString().getBytes();
                            ByteBuffer respBuffer = ByteBuffer.allocate(1024);
                            respBuffer.put(bytes);
                            respBuffer.flip();

                            channel.write(respBuffer);
                            channel.close();
                        }
                    } else if (selectionKey.isWritable()) {// 是否 可以写
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("工具人查看到可以写");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
