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
public class LhResponse {
    private OutputStream outputStream = null;

    // 写一个http的响应头 =>
    public static final String respHeaderOK = "HTTP/1.1 200 OK";

    private final HashMap<String, String> headers = new HashMap<>();

    // 在创建LhResponse时 传入的outputStream是和Socket关联的
    public LhResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
        headers.put("Content-Type", "text/html;charset=UTF-8");
        headers.put("Date", String.valueOf(new java.sql.Date(System.currentTimeMillis()).toLocaleString()));// sql类型的时间
        headers.put("Content-Length", "0");// 长度
        headers.put("Server", "LHTomCat");// 服务器名
        init();
    }

    /**
     * 初始化
     */
    private void init() {

    }

    /**
     * 写
     */
    public void write(File file) {
        // 修改状态行
        headers.put("Content-Length", String.valueOf(file.length()));// 文件长度

        // 根据文件拓展名去修改文件类型
        setFileTypeByName(file);

        // 得到状态码是200 Ok的响应头部
        byte[] buffer = getOkHeardBytes();

        // 添加身体
        try {
            outputStream.write(buffer);
            outputStream.flush();
            // 添加body 输出文件
            InputStream inputStream = new FileInputStream(file);
            // 写文件  把文件内容写到流里面
            byte[] bytes = new byte[1024];
            while (true) {
                int readLine = inputStream.read(bytes);
                if (readLine < 0) break;// 如果读不到会返回-1
                outputStream.write(bytes, 0, readLine);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 写
     */
    public void write(int code, String data) {
        if (code == 404) {
            String respHeader = "HTTP/1.1 404 Not Found";
            headers.put("Content-Type", "text/html;charset=utf-8");
            byte[] buffer = data.getBytes();
            headers.put("Content-Length", buffer.length + "");
        }
    }

    /**
     * 写入json
     *
     * @param data json数据
     */
    public void writeToJson(String data) {
        int length = data.getBytes().length;
        // 添加状态行
        headers.put("Content-Length", String.valueOf(length));// json字符串长度
        headers.put("Content-Type", "application/Json");// json类型
        byte[] buffer = getOkHeardBytes();// 得到成功响应的头部  状态码为200
        System.out.println(headers);
        try {
            outputStream.write(buffer);
            outputStream.flush();

            // 写入内容 todo 未写入完整
            outputStream.write(data.getBytes());
            outputStream.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到成功响应的头部
     *
     * @return {@link byte[]}
     */
    private byte[] getOkHeardBytes() {
        // 添加头部
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(respHeaderOK);
        stringBuffer.append("\r\n");// 回车换行符
        // 遍历集合
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            stringBuffer.append(key);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            stringBuffer.append("\r\n");
        }
        stringBuffer.append("\r\n");
        // 写入头部
        byte[] buffer = stringBuffer.toString().getBytes();
        return buffer;
    }

    /**
     * 获取文件类型
     *
     * @param file 文件
     */
    private void setFileTypeByName(File file) {
        String extension = "html";
        String name = file.getName();
        String[] splitArr = name.split("\\.");
        if (splitArr.length > 1) {
            extension = splitArr[splitArr.length - 1].toLowerCase();// 变成小写
        }
        if ("html".equals(extension)) {
            headers.put("Content-Type", "text/html;charset=UTF-8");
        } else if ("css".equals(extension)) {
            headers.put("Content-Type", "text/css;charset=UTF-8");
        } else if ("js".equals(extension)) {
            headers.put("Content-Type", "text/javascript;charset=UTF-8");
        } else if ("json".equals(extension)) {
            headers.put("Content-Type", "application/json;charset=UTF-8");
        } else if ("xml".equals(extension)) {
            headers.put("Content-Type", "application/xml;charset=UTF-8");
        } else if ("png".equals(extension)) {
            headers.put("Content-Type", "image/png");
        } else if ("jpg".equals(extension) || "jpeg".equals(extension)) {
            headers.put("Content-Type", "image/jpeg");
        } else if ("gif".equals(extension)) {
            headers.put("Content-Type", "image/gif");
        } else if ("pdf".equals(extension)) {
            headers.put("Content-Type", "application/pdf");
        } else if ("txt".equals(extension)) {
            headers.put("Content-Type", "text/plain;charset=UTF-8");
        }

    }

    /**
     * 获取输出流
     * 当我们需要给浏览器返回数据时 可以通过LhResponse对象 的输出流完成
     *
     * @return {@link OutputStream}
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

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
