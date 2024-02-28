package lhweb.asia.LHTomCat.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 罗汉请求
 * lh请求
 * 1 LhRequest作用是封装http请求的数据
 * 2 比如 method get/post uri,参数列表
 * 例如：http://localhost:8888/lhServletV3?name=1&password=2 封装的就是 /lhServletV3?name=1&password=2
 * 3 LhRequest等价原生servlet 中的HttpServletRequest
 * <p>
 * <p>
 * 4 现在只考虑get请求
 *
 * @author 罗汉
 * @date 2024/02/25
 */
public class LhRequest {
    private String method;// 访问方法
    private String uri;// url中的一部分
    private String url;// 访问链接 全称
    private String body;// 请求体
    private String protocol;// 协议版本
    private InputStream inputStream = null;
    // 参数列表 key value 所以用hashMap
    private final HashMap<String, String> parametersMap = new HashMap<>();// 请求头url中的参数列表
    private final HashMap<String, String> headers = new HashMap<>();// 请求头中的参数

    public LhRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        // 完成对http请求数据的封装
        init();
    }

    /**
     * 初始化
     */
    private void init() { // 为了读取方便 inputStream-> BufferedReader  InputStreamReader： 转换流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
            /*原始请求 http://localhost:8888/hhh?name=1&password=2
             * GET /hhh?name=1&password=2 HTTP/1.1  Host: localhost:8888
             * */
            String requestLine = bufferedReader.readLine();// 读取第一行 GET /lhServletV3?name=1&password=2 HTTP/1.1
            System.err.println("requestLine:" + requestLine);
            // 按照空格分成一个数组
            String[] requestLineArr = null;
            // 只有requestLine不为空才能往下进行
            if (requestLine != null) {
                requestLineArr = requestLine.split(" ");
                if (requestLineArr.length < 3) {
                    throw new IOException("请求格式错误~"); // 请求头一般是    请求方法 请求路径 请求协议
                }
                // 得到method
                method = requestLineArr[0];
                // 得到url
                url = requestLineArr[1];
                // 得到protocol
                protocol = requestLineArr[2];

                // 解析请求头中url中的参数列表
                getParameterByUrl(requestLineArr);

                // 解析请求头中的参数（第一行除外）
                getParameterByHeard(bufferedReader);

                // 解析请求体
                getBodys(bufferedReader);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析请求体
     *
     * @param bufferedReader 缓冲读者
     */
    private void getBodys(BufferedReader bufferedReader) {
        String contentType = getHeardParameter("Content-Type");
        String contentLength = getHeardParameter("Content-Length");
        // 获取Body的长度
        int length=!(contentLength.length() == 0) && !"".equals(contentType)?Integer.parseInt(contentLength):0;
        // 接收请求体
        if (length > 0) {
            char[] buffer = new char[length];
            int totalRead = 0; // 记录已经读取的字符数
            // io的循环读取
            while (totalRead < length) {
                try {
                    int read = bufferedReader.read(buffer, totalRead, length - totalRead); // 从总字符数位置开始读取
                    if (read < 0) {
                        break;
                    }
                    totalRead += read; // 更新已读取的字符数
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // 将char数组转换为字符串
            body = new String(buffer);
            System.out.println("body: " + body);
            //解析请求体

            String[] parametersPair = body.split("&");//body: action=register&username=12312&password=3124123123
            for (String parameterPair : parametersPair) {
                // parameterVal["action","register"]
                String[] parameterVal = parameterPair.split("=");
                if (parameterVal.length == 2) {// 说明的的确确有参数值
                    // 放入到parametersMap里去
                    parametersMap.put(parameterVal[0], parameterVal[1]);
                }
            }
        }
    }

    /**
     * 获取请求头中的参数列表
     *
     * @param bufferedReader 缓冲读者
     */
    private void getParameterByHeard(BufferedReader bufferedReader) {
        try {
            String requestLine;
            String[] splitArr;
            while (true) {
                requestLine = bufferedReader.readLine();
                if (requestLine == null || "".equals(requestLine)) {// 接受到回车换行就退出
                    break;
                } else {
                    splitArr = requestLine.split(": ");
                    headers.put(splitArr[0], splitArr[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 通过请求头中的第一行的url获取请求的参数参数
     * 并把这个参数封装到参数的map集合中parametersMap
     *
     * @param requestLineArr 请求线路arr
     */
    private void getParameterByUrl(String[] requestLineArr) {
        // System.out.println("requestLineArr:"+ Arrays.toString(requestLineArr));//requestLineArr:[GET, /aa.html?a=1&b=2&c=3, HTTP/1.1]
        // 得到uri：/ 可以用正则表达式 这里就简单的用字符串切割了
        int index = 0;
        // 看看是否有参数列表
        index = requestLineArr[1].indexOf("?");
        // System.out.println("index=" + index);//index=8
        if (index == -1) {// 说明没有参数列表 存储的就是/+   例如/haha
            uri = requestLineArr[1];
        } else {// 有参数列表
            //[0,index)
            uri = requestLineArr[1].substring(0, index);
            // 获取参数列表 放到parametersMap中去
            // parameters 相当于 name=1&password=2
            String parameters = requestLineArr[1].substring(index + 1);
            // System.out.println("parameters: " + parameters);//parameters: a=1&b=2&c=3
            String[] parametersPair = parameters.split("&");
            // System.out.println("parametersPair: " + Arrays.toString(parametersPair));//parametersPair: [a=1, b=2, c=3]
            // 防止用户提交时http://localhost:8888/haha?   后面只给?不给参数
            // if(null!=parametersPair&& "".equals(parametersPair)){
            // 再次分割 parameterPair=    name=1
            for (String parameterPair : parametersPair) {
                // parameterVal["name","1"]
                String[] parameterVal = parameterPair.split("=");
                if (parameterVal.length == 2) {// 说明的的确确有参数值
                    // 放入到parametersMap里去
                    parametersMap.put(parameterVal[0], parameterVal[1]);
                }
            }
        }
    }

    // request对象有一个特别重要的方法
    public String getParameter(String name) {
        return parametersMap.getOrDefault(name, "");

    }

    // request对象有一个特别重要的方法
    public String getHeardParameter(String name) {
        return headers.getOrDefault(name, "");

    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, String> getParametersMap() {
        return parametersMap;
    }

    public String getBody() {
        return body;
    }

    public String getProtocol() {
        return protocol;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "LhRequest{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", url='" + url + '\'' +
                ", body='" + body + '\'' +
                ", protocol='" + protocol + '\'' +
                ", inputStream=" + inputStream +
                ", parametersMap=" + parametersMap +
                ", headers=" + headers +
                '}';
    }
}
