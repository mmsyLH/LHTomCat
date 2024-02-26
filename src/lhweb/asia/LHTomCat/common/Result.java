package lhweb.asia.LHTomCat.common;

import java.io.Serializable;
import java.util.Map;

/**
 * 结果
 * 后端统一返回结果
 *
 * @param <T>
 * @author 罗汉
 * @date 2024/02/26
 */
public class Result<T> implements Serializable {

    private int code;//200成功  0失败
    private String msg;
    private Map<String, String> msgMap;
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 200;
        return result;
    }
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.msg=msg;
        result.code = 200;
        return result;
    }
    public static <T> Result<T> success(T object,String msg) {
        Result<T> result = new Result<>();
        result.data = object;
        result.code = 200;
        result.msg = msg;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(Map<String, String> map) {
        Result<T> result = new Result<>();
        result.msgMap = map;
        result.code = 0;
        return result;
    }
}