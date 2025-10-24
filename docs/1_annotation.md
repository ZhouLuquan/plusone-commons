## 1. 注解
|注解|说明|
|--|--|
| **StaticFactoryMethod**             | **标识静态工厂方法**。 *《Effective Java》* 的 **Item1** 建议考虑用静态工厂方法替换构造器， 因而考虑有一个注解可以标记一下静态工厂方法，以和其它方法进行区分。|
| **ReaderMethod** / **WriterMethod** | **分别标识读方法（如 getter）或写方法（如 setter）**。<br>*最早是写了一个集合类，为了方便判断使用读写锁时，哪些情况下使用读锁，哪些情况下使用写锁。*|
| **UnsupportedOperation**            | **标识该方法不被支持或没有实现**，将抛出 `UnsupportedOperationException`。 为了方便在使用时，不需要点进源码，就能知道该方法没有实现。|
| **Virtual**                         | Java 非 final 的实例方法，对应 C++/C# 中的虚方法，允许被子类覆写。 **Virtual 注解旨在设计父类时，强调该方法父类虽然有默认实现，但子类可以根据自己的需要覆写**。|
| **ValueObject**                     | 标记一个类，表示其作为**值对象**，区别于 Entity。|
