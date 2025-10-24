## 2. 集合

### 2.1. CollectionTools

简单的集合工具类，包含判空等常用方法。

### 2.2. MapModifier

Map 修改器。封装一系列对 Map 数据的修改操作，修改 Map 的数据。可以用于 Map 的数据初始化等操作。

```java
// MapModifier
MapModifier<String, Object> modifier = new MapModifier<String, Object>()
    .putAll(commonProperties)
    .put("username", "Ben")
    .put("accountStatus", LOCKED);

// 从 Supplier 中获取 Map，并修改数据
Map<String, Object> map = modifier.getAndModify(HashMap::new);

// 可以灵活使用不同 Map 类型的不同构造器
Map<String, Object> map = modifier.getAndModify(() -> new HashMap<>(8));
Map<String, Object> map = modifier.getAndModify(() -> new HashMap<>(anotherMap));
Map<String, Object> map = modifier.getAndModify(TreeMap::new);
Map<String, Object> map = modifier.getAndModify(ConcurrentHashMap::new);

// 修改已有的 Map
modifier.modify(map);

// 创建一个有初始化数据的不可变的 Map
Map<String, Object> map = modifier.getUnmodifiableMap();

// 链式调用创建并初始化数据
Map<String, Object> map = new MapModifier<String, Object>()
    .putAll(commonProperties)
    .put("username", "Ben")
    .put("accountStatus", LOCKED)
    .getAndModify(HashMap::new);
```
