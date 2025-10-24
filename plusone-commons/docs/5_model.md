## 5. 数据模型

### 5.1. 业务模型

|     | 类型 | 描述 |
| --- | --- | --- |
| 接口 | `IDCardNumber` | 身份证号 |
| 值对象 | » `Chinese2ndGenIDCardNumber` | 中国二代居民身份证号 |
| 值对象 | `SemVer` | 语义化版本号 |
| 值对象（枚举）| `Gender` | 性别 |
| ~~值对象（抽象类）~~| ~~`ValidatableStringRecord`~~ | ~~带校验的字符串值对象~~ |

### 5.2. 数据传输对象

#### 5.2.1. 分页查询

`PagingAndSortingQueryParams` （分页排序查询参数）

`PagingAndSortingQueryParams` 包含三个主要的属性：
- **size** - 每页显示的记录数
- **pageNum** - 当前页码
- **orderBy** - 排序条件

分页必须伴随着排序，不然可能出现同一个对象重复出现在不同页，有的对象不被查询到的情况。

其中 `orderBy` 是一个 `List<String>`，可以指定多个排序条件，每个排序条件是一个字符串， 格式为“**属性名-ASC**”或“**属性名-DESC**”，分别表示升序和降序。

例如当 `orderBy` 的值为 `["name-ASC","age-DESC"]`，意味着要按 `name` 进行升序排列，`name` 相同的情况下则按 `age` 进行降序排列。

用户可继承 `PagingAndSortingQueryParams` 构建自己的分页查询入参，子类需在构造器中调用 `PagingAndSortingQueryParams` 的构造器，传入一个 `PagingParamsBuilder` 用于构建分页参数。同一场景下，复用一个 {@link PagingParamsBuilder} 实例即可。

构建 `PagingParamsBuilder` 时，需传入一个 `Map` 作为可排序字段的白名单，`key` 是供前端指定用于排序的**属性名**，`value` 是对应数据库中的**字段名**，只有在白名单中指定的属性名才允许作为排序条件。

```java
@ToString(callSuper = true)
class AccountQueryParams extends PagingAndSortingQueryParams {

    private static final Map<String, String> PROPERTY_COLUMN_MAP = ImmutableMap.<String, String>builder()
            .put("id", "id")
            .put("username", "username")
            .build();

    private static final PagingParamsBuilder PAGING_PARAMS_BUILDER = PagingAndSortingQueryParams
            .pagingParamsBuilder(20, 100, PROPERTY_COLUMN_MAP);

    public AccountQueryParams() {
        super(PAGING_PARAMS_BUILDER);
    }

    private @Getter @Setter Long id;
    private @Getter @Setter String username;
    private @Getter @Setter String email;
    private @Getter @Setter Integer status;
}
```

使用时调用 `PagingAndSortingQueryParams#buildPagingParams()` 方法获取分页参数 `PagingParams`。分页结果可以存放到 `PageResult` 中，作为出参。

```java
public PageResult<AccountVO> queryPage(AccountQueryParams params) {
    // 获取分页参数
    PagingParams pagingParams = params.buildPagingParams();
    // 从 params 获取字段查询条件，从 pagingParams 获取分页条件，查询一页数据
    List<AccountVO> list = accountQueries.queryAccountList(params, pagingParams);
    // 查询总记录数
    long count = accountQueries.countAccount(params);
    // 返回分页结果
    return PageResult.of(list, count);
}
```

#### 5.2.2. UnifiedResponse

`UnifiedResponse` 对返回给前端的数据进行封装，包含 `code`、`message`、`data`。

`UnifiedResponses` 是 `UnifiedResponse` 的工厂类。用于快速构建 `UnifiedResponse` 对象，默认的成功代码为 `2000000`。

用户可以继承 `UnifiedResponses` 实现自己的工厂类，自定义 SUCCESS_CODE 和 DEFAULT_SUCCESS_MSG，以及工厂方法。如下所示：
```java
// 自定义工厂类
public static class CustomUnifiedResponses extends UnifiedResponses {
    public static final String SUCCESS_CODE = "000";
    public static final String DEFAULT_SUCCESS_MSG = "成功";
    public static <T> UnifiedResponse<T> success() {
        return of(SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }
    public static <T> UnifiedResponse<T> success(@Nullable String message) {
        return of(SUCCESS_CODE, message);
    }
    public static <T> UnifiedResponse<T> success(@Nullable String message, @Nullable T data) {
        return of(SUCCESS_CODE, message, data);
    }
    private CustomUnifiedResponses() {
        super();
    }
}
// 使用自定义工厂类
CustomUnifiedResponses.success("查询成功", userList); // 状态码为 000
```
> 见 [issue#22 @Gitea](http://gitea.zhouxy.xyz/plusone/plusone-commons/issues/22)
