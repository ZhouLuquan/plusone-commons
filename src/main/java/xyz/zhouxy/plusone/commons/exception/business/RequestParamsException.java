/*
 * Copyright 2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.exception.business;

/**
 * RequestParamsException
 *
 * <p>
 * 用户请求参数错误
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public class RequestParamsException extends BizException {

    private static final String DEFAULT_MSG = "用户请求参数错误";

    public RequestParamsException() {
        super(DEFAULT_MSG);
    }

    public RequestParamsException(String msg) {
        super(msg);
    }

    public RequestParamsException(Throwable cause) {
        super(cause);
    }

    public RequestParamsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
