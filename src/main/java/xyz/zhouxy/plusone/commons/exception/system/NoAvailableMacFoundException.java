/*
 * Copyright 2023-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.exception.system;

/**
 * NoAvailableMacFoundException
 *
 * <p>
 * 在无法找到可访问的 Mac 地址时抛出
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public class NoAvailableMacFoundException extends SysException {
    private static final long serialVersionUID = 152827098461071551L;

    public NoAvailableMacFoundException() {
        super();
    }

    public NoAvailableMacFoundException(String message) {
        super(message);
    }

    public NoAvailableMacFoundException(Throwable cause) {
        super(cause);
    }

    public NoAvailableMacFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
