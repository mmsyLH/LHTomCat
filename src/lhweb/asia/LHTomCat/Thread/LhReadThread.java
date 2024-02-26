package lhweb.asia.LHTomCat.Thread;

import java.io.*;
import java.net.Socket;

/**
 * 罗汉读线程
 * 用来读取数据的线程
 *
 * @author 罗汉
 * @date 2024/02/25
 */
public class LhReadThread extends Thread {
    private Socket socket;

    public LhReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 这里我们可以对客户端/浏览器进行IO编程/交互
        try {
            InputStream inputStream = socket.getInputStream();
            // 把inputStream转成BufferedReader 方便按行读取
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            System.out.println("==================lhtomcat 接收到的数据如下====================");
            String msg = null;
            while (true) {
                msg = bufferedReader.readLine();
                // 如果msg为空，则说明已经读取完毕，退出循环
                if (msg == null) {
                    break;
                }
                // 如果长度为0 "" 退出
                if (msg.length() == 0) {
                    break;
                }
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
