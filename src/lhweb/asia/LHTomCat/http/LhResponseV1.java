package lhweb.asia.LHTomCat.http;

import java.io.*;
import java.util.HashMap;

/**
 * 罗汉响应
 * <p>
 * 1 LhResponse对象可以封装OutputStream(是socket关联),
 * 2 即可以通过 HspResponse对象 返回Http响应浏览器/客户端
 * 3 LhResponse对象的作用等价于原生的servlet的 HttpServletResponse
 *
 * @author 罗汉
 * @date 2024/02/25
 */
@Deprecated
public class LhResponseV1 {
    // 与Socket关联的输出流
    private OutputStream outputStream;

    // Http响应状态行
    public static final String respHeaderOK = "HTTP/1.1 200 OK";

    // Http响应头部信息
    private final HashMap<String, String> headers = new HashMap<>();

    /**
     * 创建LhResponse对象
     *
     * @param outputStream 与Socket关联的输出流
     */
    public LhResponseV1(OutputStream outputStream) {
        this.outputStream = outputStream;
        // 设置默认的Http响应头部信息
        headers.put("Content-Type", "text/html;charset=UTF-8");
        headers.put("Date", String.valueOf(new java.sql.Date(System.currentTimeMillis()).toLocaleString()));
        headers.put("Content-Length", "0");
        headers.put("Server", "LHTomCat");
    }



    /**
     * 向浏览器/客户端发送文件
     *
     * @param file 要发送的文件
     */
    public void write(File file) {
        // 修改状态行中的Content-Length字段为文件长度
        headers.put("Content-Length", String.valueOf(file.length()));

        // 根据文件扩展名设置Content-Type字段
        setFileTypeByName(file);

        // 获取Http响应头部字节数组
        byte[] buffer = getOkHeaderBytes();

        // 发送Http响应头部
        try {
            outputStream.write(buffer);
            outputStream.flush();

            // 发送文件内容
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int readLine;
            while ((readLine = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readLine);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向浏览器/客户端发送指定状态码和数据
     *
     * @param code 状态码
     * @param data 要发送的数据
     */
    public void write(int code, String data) {
        //todo
    }

    /**
     * 向浏览器/客户端发送JSON数据
     *
     * @param data JSON数据
     */
    public void writeToJson(String data) {
        int length = data.getBytes().length;
        // 设置Content-Length字段为JSON数据长度
        headers.put("Content-Length", String.valueOf(length));
        // 设置Content-Type字段为application/json
        headers.put("Content-Type", "application/json;charset=UTF-8");
        // 获取成功响应的Http头部字节数组
        byte[] buffer = getOkHeaderBytes();
        try {
            // 发送Http响应头部
            outputStream.write(buffer);
            outputStream.flush();

            // 发送JSON数据内容
            outputStream.write(data.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取成功响应的Http头部字节数组
     *
     * @return 成功响应的Http头部字节数组
     */
    private byte[] getOkHeaderBytes() {
        // 构建Http头部字符串
        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(respHeaderOK);
        stringBuilder.append("\r\n");
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        // 返回Http头部字节数组
        return stringBuilder.toString().getBytes();
    }

    /**
     * 根据文件名设置Content-Type字段
     *
     * @param file 要发送的文件
     */
    private void setFileTypeByName(File file) {
        String extension = "html";
        String name = file.getName();
        String[] splitArr = name.split("\\.");
        if (splitArr.length > 1) {
            extension = splitArr[splitArr.length - 1].toLowerCase();
        }
        // 根据文件扩展名设置Content-Type字段
        switch (extension) {
            case "html" -> headers.put("Content-Type", "text/html;charset=UTF-8");
            case "css" -> headers.put("Content-Type", "text/css;charset=UTF-8");
            case "js" -> headers.put("Content-Type", "text/javascript;charset=UTF-8");
            case "json" -> headers.put("Content-Type", "application/json;charset=UTF-8");
            case "xml" -> headers.put("Content-Type", "application/xml;charset=UTF-8");
            case "png" -> headers.put("Content-Type", "image/png");
            case "jpg", "jpeg" -> headers.put("Content-Type", "image/jpeg");
            case "gif" -> headers.put("Content-Type", "image/gif");
            case "pdf" -> headers.put("Content-Type", "application/pdf");
            case "txt" -> headers.put("Content-Type", "text/plain;charset=UTF-8");
        }
    }

    /**
     * 获取输出流
     * 当需要向浏览器返回数据时，可以通过LhResponse对象的输出流完成。
     *
     * @return 输出流
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * 获取Http响应头部信息
     *
     * @return Http响应头部信息
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "LhResponse{" +
                "outputStream=" + outputStream +
                ", headers=" + headers +
                '}';
    }
}
