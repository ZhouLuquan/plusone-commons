package xyz.zhouxy.plusone.commons.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 对返回给前端的数据进行封装
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestfulResult {

    public static final int SUCCESS_STATUS = 2000000;

    private final Object status;
    private final String message;
    private final Object data;

    private RestfulResult(Object status, String message) {
        this(status, message, null);
    }

    public static RestfulResult success() {
        return new RestfulResult(SUCCESS_STATUS, "操作成功");
    }

    public static RestfulResult success(String message) {
        return new RestfulResult(SUCCESS_STATUS, message);
    }

    public static RestfulResult success(String message, Object data) {
        return new RestfulResult(SUCCESS_STATUS, message, data);
    }

    public static RestfulResult error() {
        return new RestfulResult(500000, "未知错误");
    }

    public static RestfulResult error(Object status, String message) {
        return new RestfulResult(status, message);
    }

    public static RestfulResult error(Object status, String message, Object data) {
        return new RestfulResult(status, message, data);
    }

    public static RestfulResult error(Object status, Throwable e) {
        return new RestfulResult(status, e.getMessage());
    }
}
