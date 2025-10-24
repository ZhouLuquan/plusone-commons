## 7. 工具类

### 7.1. 数组工具（ArrayTools）

| 方法 | 描述 |
| --- | --- |
| `isEmpty`              | 判断数组是否为空 |
| `isNotEmpty`           | 判断数组是否不为空 |
| `isAllElementsNotNull` | 判断数组中所有元素是否不为空 |
| `concat`               | 拼接数组 |
| `repeat`               | 重复数组中的元素 |
| `fill`                 | 填充数组 |
| `indexOf`              | 获取元素在数组中的索引 |
| `lastIndexOf`          | 获取元素最后出现在数组中的索引 |
| `contains`             | 判断数组中是否包含某个元素 |

### 7.2. 断言工具（AssertTools）

`AssertTools` 不封装过多判断逻辑，鼓励充分使用项目中的工具类对数据进行判断：

```java
AssertTools.checkArgument(StringUtils.hasText(str), "The argument cannot be blank.");
AssertTools.checkState(ArrayUtils.isNotEmpty(result), "The result cannot be empty.");
AssertTools.checkCondition(!CollectionUtils.isEmpty(roles),
    () -> new InvalidInputException("The roles cannot be empty."));
AssertTools.checkCondition(RegexTools.matches(email, PatternConsts.EMAIL),
    "must be a well-formed email address");
```

### 7.3. 枚举工具

#### ~~7.3.1 枚举类（Enumeration）（已废弃）~~

~~`Enumeration` 的实现来自于 .net 社区。因为 C# 本身的枚举不带行为，所以继承自 `Enumeration` 类，以实现带行为的枚举常量。~~

**~~但 Java 的枚举可以带行为，故大多数情况下不需要这种设计。~~**

#### 7.3.2 Enum 工具类（EnumTools）

用于枚举的 ordinal 和枚举值的转换等操作。

由于不推荐使用枚举的 ordinal，**故大多数方法已废弃**。更推荐的实现是枚举实现 `IWithCode` 之类的接口，在枚举中提供枚举值和枚举码的转换。

### 7.4. ID 生成器

#### 7.4.1. ID 生成器（IdGenerator）

- 提供了 `UUID` 相关的方法
  | 方法 | 描述 |
  | --- | --- |
  | newUuid          | 获取新的 `UUID` |
  | uuidString       | 获取新的 UUID 字符串 |
  | simpleUuidString | 获取新的 UUID 字符串（无连接符） |
  | toSimpleString   | 将 `UUID` 转换为无连接符的字符串 |
- 使用 `IdWorker` *（来自 **Seata** 的雪花算法的变种）* 生成分布式唯一 ID

#### 7.4.2. IdWorker

来自 [Apache Seata](https://seata.apache.org/) 的 [`org.apache.seata.common.util.IdWorker`](https://github.com/apache/incubator-seata/blob/2.x/common/src/main/java/org/apache/seata/common/util/IdWorker.java)，是雪花算法的变种。

详细介绍参考以下文章：
- [Seata基于改良版雪花算法的分布式UUID生成器分析](https://seata.apache.org/zh-cn/blog/seata-analysis-UUID-generator)
- [关于新版雪花算法的答疑](https://seata.apache.org/zh-cn/blog/seata-snowflake-explain)
- [在开源项目中看到一个改良版的雪花算法，现在它是你的了。](https://juejin.cn/post/7264387737276203065)
- [关于若干读者，阅读“改良版雪花算法”后提出的几个共性问题的回复。](https://juejin.cn/post/7265516484029743138)

#### 7.4.3. SnowflakeIdGenerator

`SnowflakeIdGenerator` 是原版的雪花算法的实现

### 7.5. 树构建器（TreeBuilder）

`TreeBuilder` 是一个树构建器，用于将列表数据构建为树结构。

`TreeBuilder` 构造器的入参：
- **identityGetter**: 从节点中获取其标识的逻辑
- **parentIdentityGetter**: 获取父节点标识的逻辑
- **addChild**: 添加子节点的逻辑
- **defaultComparator**: 默认的 Comparator，用于排序

> **注意：`TreeBuilder` 的 `buildTree` 方法，会直接更改列表中的节点。设计初衷是将查询到的列表，构建成为树结构之后直接返回给前端，如果需要，请在调用之前做深拷贝，然后再将深拷贝的结果作为入参传入。**

以下示例演示 `TreeBuilder` 的使用：

#### 7.5.1. 处理相对复杂的 entity

假设有如下的实体类：
```java
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
class Menu implements Serializable {
    protected final @Getter String parentMenuCode;
    protected final @Getter String menuCode;
    protected final @Getter String title;
    protected final @Getter int orderNum;

    private static final long serialVersionUID = 20240917181424L;
}

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
class MenuItem extends Menu {

    private final @Getter String url;

    private MenuItem(String parentMenuCode, String menuCode, String title, String url, int orderNum) {
        super(parentMenuCode, menuCode, title, orderNum);
        this.url = url;
    }

    static MenuItem of(String parentMenuCode, String menuCode, String title, String url, int orderNum) {
        return new MenuItem(parentMenuCode, menuCode, title, url, orderNum);
    }

    static MenuItem of(String menuCode, String title, String url, int orderNum) {
        return new MenuItem(null, menuCode, title, url, orderNum);
    }

    private static final long serialVersionUID = 20240917181910L;
}

@ToString(callSuper = true)
class MenuList extends Menu {

    private List<Menu> children;

    private MenuList(String parentMenuCode, String menuCode, String title, int orderNum) {
        super(parentMenuCode, menuCode, title, orderNum);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title, int orderNum) {
        return new MenuList(parentMenuCode, menuCode, title, orderNum);
    }

    static MenuList of(String menuCode, String title, int orderNum) {
        return new MenuList(null, menuCode, title, orderNum);
    }

    @SuppressWarnings("unused")
    static MenuList of(String menuCode, String title, Iterable<Menu> children, int orderNum) {
        return of(null, menuCode, title, children, orderNum);
    }

    static MenuList of(String parentMenuCode, String menuCode, String title, Iterable<Menu> children,
            int orderNum) {
        final MenuList instance = of(parentMenuCode, menuCode, title, orderNum);
        children.forEach(instance::addChild);
        return instance;
    }

    public void addChild(Menu child) {
        if (this.children == null) {
            this.children = Lists.newArrayList();
        }
        this.children.add(child);
    }

    private static final long serialVersionUID = 20240917181917L;
}
```

其中，`Menu` 表示菜单节点，其子类 `MenuItem` 表示菜单项，在树中作为叶子节点，另一子类 `MenuList` 表示菜单列表，其子菜单放在 `children` 字段中。`MenuList` 提供了 `addChild` 方法用于将子菜单添加到 `children` 中。

使用以下方式构建并使用 `TreeBuilder`：
```java
// 创建 TreeBuilder
TreeBuilder<Menu, MenuList, String> treeBuilder = new TreeBuilder<>(
        // getMenuCode 方法获取节点标识
        Menu::getMenuCode,
        // getParentMenuCode 方法获取父节点标识，如果父节点不存在，返回 Optional.empty()
        menu -> Optional.ofNullable(menu.getParentMenuCode()),
        // addChild 方法用于将子节点添加到父节点的 children 中
        MenuList::addChild,
        // 默认的 Comparator，使用 orderNum 进行排序
        Comparator.comparing(Menu::getOrderNum));

// 需要的话进行深拷贝
List<Menu> clonedMenus = menus.stream().map(ObjectUtil::clone).collect(Collectors.toList());
// 按照创建时设置的逻辑，构建树结构
List<Menu> result = treeBuilder.buildTree(clonedMenus);
```

#### 7.5.2. 处理 POJO

`TreeBuilder` 也可以处理 POJO，只需要自定义 `TreeBuilder` 所需的入参即可。

```java
// POJO
@Data
class Menu implements Serializable {
    private final String parentMenuCode;
    private final String menuCode;
    private final String title;
    private final int orderNum;

    private final String url;
    private List<Menu> children;

    private static final long serialVersionUID = 1298482252210272617L;
}
```

使用以下方式构建并使用 `TreeBuilder`：
```java
// 创建 TreeBuilder
TreeBuilder<Menu, MenuList, String> treeBuilder = new TreeBuilder<>(
        // getMenuCode 方法获取节点标识
        Menu::getMenuCode,
        // getParentMenuCode 方法获取父节点标识，如果父节点不存在，返回 Optional.empty()
        menu -> Optional.ofNullable(menu.getParentMenuCode()),
        // 自定义 addChild 逻辑
        (menuList, child) -> {
            List<Menu> children = menuList.getChildren();
            if (children == null) {
                children = Lists.newArrayList();
                menuList.setChildren(children);
            }
            children.add(child);
        },
        // 默认的 Comparator，使用 orderNum 进行排序
        Comparator.comparing(Menu::getOrderNum));

// 按照创建时设置的逻辑，构建树结构
List<Menu> result = treeBuilder.buildTree(clonedMenus);
```

### 7.6. Ref
`Ref` 包装了一个值，表示对该值的应用。

C# 中允许通过 ref 参数修饰符，将值返回给调用端：
```csharp
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

### 7.7 其它工具类
- **`BigDecimals`**: BigDecimal 工具
- **`Numbers`**: 数字工具
- **`OptionalTools`**: Optional 工具
- **`RandomTools`**: 随机工具
- **`RegexTools`**: 正则工具
- **`StringTools`**: 字符串工具
- **`ZipTools`**: zip 工具
