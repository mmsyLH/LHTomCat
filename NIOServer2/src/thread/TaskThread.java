package thread;

import http.HttpRequest;
import http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author :罗汉
 * @date : 2024/3/6
 */
public class TaskThread  extends Thread {
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private SocketChannel socketChannel;

    public TaskThread(HttpRequest httpRequest, HttpResponse httpResponse, SocketChannel socketChannel) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        String uri = httpRequest.getUri();
        File file = new File("12306/webapps" + uri);
        if (file.exists()) {
            httpResponse.write(file);
        }
        try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
