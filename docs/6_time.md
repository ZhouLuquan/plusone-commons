## 6. 时间 API

### 6.1. 季度

模仿 JDK 的 `java.time.Month` 和 `java.time.YearMonth`， 实现 `xyz.zhouxy.plusone.commons.time.Quarter`、`xyz.zhouxy.plusone.commons.timeYearQuarter`，对季度进行建模。

*这两个类的代码修改后，也提交给了 **hutool**。见 gitee
 上的 [pr#1324](https://gitee.com/chinabugotech/hutool/pulls/1324)*。

### 6.2. DateTimeTools

`xyz.zhouxy.plusone.commons.util.DateTimeTools` 提供了包含 Java 旧的时间 API 和 `java.time` API 在内的日期时间的常用操作。

### 6.3. JodaTimeTools

`xyz.zhouxy.plusone.commons.util.JodaTimeTools` 提供了 JodaTime 和 `java.time` API 相互转换的工具方法：
- `toJodaInstant`
- `toJavaInstant`
- `toJodaDateTime`
- `toZonedDateTime`
- `toJodaLocalDateTime`
- `toJavaLocalDateTime`
- `toJavaZone`
- `toJodaZone`
