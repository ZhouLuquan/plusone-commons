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

package xyz.zhouxy.plusone.commons.exception.system;

/**
 * DataOperationResultException
 *
 * <p>
 * 当数据操作的结果不符合预期时抛出。
 * </p>
 *
 * <p>
 * 比如当一个 insert 或 update 操作时，预计影响数据库中的一行数据，但结果却影响了零条数据或多条数据，
 * 当出现这种始料未及的诡异情况时，抛出 {@link DataOperationResultException} 并回滚事务。
 * 后续需要排查原因。
 * </p>
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public final class DataOperationResultException extends SysException {

    private static final String DEFAULT_MSG = "数据操作的结果不符合预期";

    public DataOperationResultException() {
        super(DEFAULT_MSG);
    }

    public DataOperationResultException(String msg) {
        super(msg);
    }

    public DataOperationResultException(Throwable cause) {
        super(cause);
    }

    public DataOperationResultException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
