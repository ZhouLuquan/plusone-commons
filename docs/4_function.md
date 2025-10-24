## 4. - 函数式编程

### 4.1. PredicateTools

`PredicateTools` 用于 `Predicate` 的相关操作。

### 4.2. Functional interfaces

补充可能用得上的函数式接口：

| Group | FunctionalInterface | method |
| --- | --- | --- |
| UnaryOperator | **BoolUnaryOperator**    | boolean applyAsBool (boolean) |
| UnaryOperator | **CharUnaryOperator**    | char applyAsChar(char) |
| Throwing      | **Executable**           | void execute() throws E |
| Throwing      | **ThrowingConsumer**     | void accept(T) throws E |
| Throwing      | **ThrowingFunction**     | R apply(T) throws E |
| Throwing      | **ThrowingPredicate**    | boolean test(T) throws E |
| Throwing      | **ThrowingSupplier**     | T get() throws E |
| Optional      | **OptionalSupplier**     | Optional<T> get() throws E |
| Optional      | **ToOptionalBiFunction** | Optional<R> apply(T,U) |
| Optional      | **ToOptionalFunction**   | Optional<R> apply(T) |
