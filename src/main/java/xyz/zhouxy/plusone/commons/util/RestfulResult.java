/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
