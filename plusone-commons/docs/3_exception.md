## 3. 异常

### 3.1. 业务异常
|异常|描述|
|---|---|
|`BizException`|**业务异常**<br>*用户可继承 `BizException` 自定义业务异常。*|
|» `RequestParamsException`|**用户请求参数错误**|
|» » `InvalidInputException`|**用户输入内容非法**<br>00 - **DEFAULT** (用户输入内容非法)<br>01 - **CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS** (包含非法恶意跳转链接)<br>02 - **CONTAINS_ILLEGAL_WORDS** (包含违禁敏感词)<br>03 - **PICTURE_CONTAINS_ILLEGAL_INFORMATION** (图片包含违禁信息)<br>04 - **INFRINGE_COPYRIGHT** (文件侵犯版权)|

### 3.2. 系统异常
|异常|描述|
|---|---|
|`SysException`|**系统异常**（表示技术异常）<br>*用户可继承 `SysException` 自定义系统异常。*|
|» `DataOperationResultException`|**数据操作的结果不符合预期**|
|» `NoAvailableMacFoundException`|**无法找到可访问的 Mac 地址**|

### 3.3. 其它异常
|异常|描述|
|---|---|
|`DataNotExistsException`|**数据不存在异常**|
|`ParsingFailureException`|**数据解析异常**<br>00 - **DEFAULT** (解析失败)<br>10 - **NUMBER_PARSING_FAILURE** (数字转换失败)<br>20 - **DATE_TIME_PARSING_FAILURE** (时间解析失败)<br>30 - **JSON_PARSING_FAILURE** (JSON 解析失败)<br>40 - **XML_PARSING_FAILURE** (XML 解析失败)|

### 3.4. 多类型异常

异常在不同场景下被抛出，可以用不同的枚举值，表示不同的场景类型。

异常实现 `IMultiTypesException` 的 `getType` 方法，返回对应的场景类型。

枚举实现 `IExceptionType` 接口，表示不同的异常场景。也可以实现 `IExceptionFactory`，用于创建对应场景的异常。

```java
public final class LoginException
        extends RuntimeException
        implements IMultiTypesException<LoginException.Type> {
    private static final long serialVersionUID = 881293090625085616L;
    private final Type type;
    private LoginException(@Nonnull Type type, @Nonnull String message) {
        super(message);
        this.type = type;
    }

    private LoginException(@Nonnull Type type, @Nonnull Throwable cause) {
        super(cause);
        this.type = type;
    }

    private LoginException(@Nonnull Type type,
                           @Nonnull String message,
                           @Nonnull Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    @Override
    public @Nonnull Type getType() {
        return this.type;
    }

    // ...

    public enum Type implements IExceptionType<String>, IExceptionFactory<LoginException> {
        DEFAULT("00", "当前会话未登录"),
        NOT_TOKEN("10", "未提供token"),
        INVALID_TOKEN("20", "token无效"),
        TOKEN_TIMEOUT("30", "token已过期"),
        BE_REPLACED("40", "token已被顶下线"),
        KICK_OUT("50", "token已被踢下线"),
        ;

        @Nonnull
        private final String code;
        @Nonnull
        private final String defaultMessage;

        Type(@Nonnull String code, @Nonnull String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
        }

        @Override
        public @Nonnull String getCode() {
            return code;
        }

        @Override
        public @Nonnull String getDefaultMessage() {
            return defaultMessage;
        }

        @Override
        public @Nonnull LoginException create() {
            return new LoginException(this, this.defaultMessage);
        }

        @Override
        public @Nonnull LoginException create(@Nonnull String message) {
            return new LoginException(this, message);
        }

        @Override
        public @Nonnull LoginException create(@Nonnull Throwable cause) {
            return new LoginException(this, cause);
        }

        @Override
        public @Nonnull LoginException create(@Nonnull String message, @Nonnull Throwable cause) {
            return new LoginException(this, message, cause);
        }
    }
}
```

使用时，可以使用这种方式创建并抛出异常：
```java
throw LoginException.Type.TOKEN_TIMEOUT.create();
```


