## 8. 其它内容

### 8.1. IWithCode

对于类似枚举这样的类型，通常需要设置固定的码值表示对应的含义。 可实现 `IWithCode`、`IWithIntCode`、`IWithLongCode`，便于在需要的地方对这些接口的实现进行处理。

### 8.2. 正则常量

`RegexConsts` 包含常见正则表达式；`PatternConsts` 包含对应的 `Pattern` 对象
