## 一、annotation - 注解
### 1. StaticFactoryMethod
标识静态工厂方法。 *《Effective Java》* 的 **Item1** 建议考虑用静态工厂方法替换构造器， 因而考虑有一个注解可以标记一下静态工厂方法，以和其它方法进行区分。

### 2. ReaderMethod 和 WriterMethod
分别标识读方法（如 getter）或写方法（如 setter）。

最早是写了一个集合类，为了方便判断使用读写锁时，哪些情况下使用读锁，哪些情况下使用写锁。

### 3. UnsupportedOperation
标识该方法不被支持或没有实现，将抛出 `UnsupportedOperationException`。 为了方便在使用时，不需要点进源码，就能知道该方法没有实现。

### 4. Virtual
Java 非 final 的实例方法，对应 C++/C# 中的虚方法，允许被子类覆写。 Virtual 注解旨在设计父类时，强调该方法父类虽然有默认实现，但子类可以根据自己的需要覆写。

### 5. ValueObject
标记一个类，表示其作为值对象，区别于 Entity。

## 二、base - 基础组件
### 1. Ref
`Ref` 包装了一个值，表示对该值的应用。

灵感来自于 C# 的 ref 参数修饰符。C# 允许通过以下方式，将值返回给调用端：
```C#
void Method(ref int refArgument)
{
    refArgument = refArgument + 44;
}

int number = 1;
Method(ref number);
Console.WriteLine(number); // Output: 45
```
`Ref` 使 Java 可以达到类似的效果，如：
```java
void method(Ref<Integer> refArgument) {
    refArgument.transformValue(i -> i + 44);
}

Ref<Integer> number = Ref.of(1);
method(number);
System.out.println(number.getValue()); // Output: 45
```
当一个方法需要产生多个结果时，无法有多个返回值，可以使用 `Ref` 作为参数传入，方法内部修改 `Ref` 的值。 调用方在调用方法之后，使用 `getValue()` 获取结果。
```java
String method(Ref<Integer> intRefArgument, Ref<String> strRefArgument) {
    intRefArgument.transformValue(i -> i + 44);
    strRefArgument.setValue("Hello " + strRefArgument.getValue());
    return "Return string";
}

Ref<Integer> number = Ref.of(1);
Ref<String> str = Ref.of("Java");
String result = method(number, str);
System.out.println(number.getValue()); // Output: 45
System.out.println(str.getValue()); // Output: Hello Java
System.out.println(result); // Output: Return string
```
### 2. IWithCode
类似于枚举这样的类型，通常需要设置固定的码值表示对应的含义。 可实现 `IWithCode`、`IWithIntCode`、`IWithLongCode`，便于在需要的地方对这些接口的实现进行处理。

## 三、collection - 集合
### 1. CollectionTools
集合工具类

## 四、constant - 常量
### 1. 正则常量
`RegexConsts` 包含常见正则表达式；`PatternConsts` 包含对应的 `Pattern` 对象

## 五、exception - 异常
### 1. MultiTypesException - 多类型异常
异常在不同场景下被抛出，可以用不同的枚举值，表示不同的场景类型。

异常实现 `MultiTypesException` 的 `MultiTypesException#getType` 方法，返回对应的场景类型。

表示场景类型的枚举实现 `MultiTypesException.ExceptionType`，其中的工厂方法用于创建对应类型的异常。
```java
public final class LoginException
        extends RuntimeException
        implements MultiTypesException<LoginException, LoginException.Type> {
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

    public enum Type implements ExceptionType {
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
        public @Nonnull LoginException create(String message) {
            return new LoginException(this, message);
        }

        @Override
        public @Nonnull LoginException create(Throwable cause) {
            return new LoginException(this, cause);
        }

        @Override
        public @Nonnull LoginException create(String message, Throwable cause) {
            return new LoginException(this, message, cause);
        }
    }
}
```
使用时，可以使用这种方式创建并抛出异常：
```java
throw LoginException.Type.TOKEN_TIMEOUT.create();
```

### 2. 业务异常
预设常见的业务异常。可继承 `BizException` 自定义业务异常。

### 3. 系统异常
预设常见的系统异常。可继承 `SysException` 自定义系统异常。

## 六、function - 函数式编程
### 1. PredicateTools
`PredicateTools` 用于 `Predicate` 的相关操作。

### 2. Functional interfaces
补充可能用得上的函数式接口：

| Group         | FunctionalInterface  | method                           |
 | ------------- | -------------------- | -------------------------------- |
 | UnaryOperator | BoolUnaryOperator    | boolean applyAsBool (boolean)    |
 | UnaryOperator | CharUnaryOperator    | char applyAsChar(char)           |
 | Throwing      | Executable           | void execute() throws E          |
 | Throwing      | ThrowingConsumer     | void accept(T) throws E          |
 | Throwing      | ThrowingFunction     | R apply(T) throws E              |
 | Throwing      | ThrowingPredicate    | boolean test(T) throws E         |
 | Throwing      | ThrowingSupplier     | T get() throws E                 |
 | Optional      | OptionalSupplier     | Optional<T> get() throws E       |
 | Optional      | ToOptionalBiFunction | Optional<R> apply(T,U)           |
 | Optional      | ToOptionalFunction   | Optional<R> apply(T)             |

## 七、model - 业务建模组件
包含业务建模可能用到的性别、身份证等元素，也包含数据传输对象，如分页查询参数、响应结果、分页结果等。

### 数据传输对象
#### 1. 分页
分页组件由 `PagingAndSortingQueryParams` 作为入参， 因为分页必须伴随着排序，不然可能出现同一个对象重复出现在不同页，有的对象不被查询到的情况， 所以分页查询的入参必须包含排序条件。

用户可继承 `PagingAndSortingQueryParams` 构建自己的分页查询入参，需在构造器中调用 `PagingAndSortingQueryParams` 的构造器，传入一个 Map 作为白名单， key 是供前端指定用于排序的**属性名**，value 是对应数据库中的**字段名**，只有在白名单中指定的属性名才允许作为排序条件。

`PagingAndSortingQueryParams` 包含三个主要的属性：
- **size** - 每页显示的记录数
- **pageNum** - 当前页码
- **orderBy** - 排序条件

其中 `orderBy` 是一个 List，可以指定多个排序条件，每个排序条件是一个字符串， 格式为“**属性名-ASC**”或“**属性名-DESC**”，分别表示升序和降序。

比如前端传入的 orderBy 为 ["name-ASC","age-DESC"]，意味着要按 name 进行升序，name 相同的情况下则按 age 进行降序。

使用时调用 `PagingAndSortingQueryParams#buildPagingParams()` 方法获取分页参数 `PagingParams`。

分页结果可以存放到 `PageResult` 中，作为出参。

#### 2. UnifiedResponse
UnifiedResponse 对返回给前端的数据进行封装，包含 `code`、`message`、`data。`

可使用 `UnifiedResponses` 快速构建 `UnifiedResponse` 对象。 `UnifiedResponses` 默认的成功代码为 "2000000"， 用户按测试类 `CustomUnifiedResponseFactoryTests` 中所示范的，继承 `UnifiedResponses` 实现自己的工厂类， 自定义 `SUCCESS_CODE` 和 `DEFAULT_SUCCESS_MSG` 和工厂方法。 见 [issue#22](http://zhouxy.xyz:3000/plusone/plusone-commons/issues/22)。

## 八、time - 时间 API
### 1. 季度
模仿 JDK 的 `java.time.Month` 和 `java.time.YearMonth`， 实现 `Quarter`、`YearQuarter`，对季度进行建模。

## 九、util - 工具类
包含树构建器（`TreeBuilder`）、断言工具（`AssertTools`）、ID 生成器（`IdGenerator`）及其它实用工具类。
