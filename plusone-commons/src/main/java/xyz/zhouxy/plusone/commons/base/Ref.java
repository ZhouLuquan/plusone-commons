/*
 * Copyright 2024-2025 the original author or authors.
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

package xyz.zhouxy.plusone.commons.base;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import javax.annotation.Nullable;

/**
 * {@link Ref} 包装了一个值，表示对该值的应用。
 *
 * <p>灵感来自于 C&num; 的 {@code ref} 参数修饰符。C&num; 允许通过以下方式，将值返回给调用端：</p>
 * <pre>
 * void Method(ref int refArgument)
 * {
 *     refArgument = refArgument + 44;
 * }
 *
 * int number = 1;
 * Method(ref number);
 * Console.WriteLine(number); // Output: 45
 * </pre>
 * {@link Ref} 使 Java 可以达到类似的效果，如：
 * <pre>
 * void method(final Ref&lt;Integer&gt; refArgument) {
 *     refArgument.transformValue(i -&gt; i + 44);
 * }
 *
 * Ref&lt;Integer&gt; number = Ref.of(1);
 * method(number);
 * System.out.println(number.getValue()); // Output: 45
 * </pre>
 * <p>
 * 当一个方法需要产生多个结果时，无法有多个返回值，可以使用 {@link Ref} 作为参数传入，方法内部修改 {@link Ref} 的值。
 * 调用方在调用方法之后，使用 {@code getValue()} 获取结果。
 *
 * <pre>
 * String method(final Ref&lt;Integer&gt; intRefArgument, final Ref&lt;String&gt; strRefArgument) {
 *     intRefArgument.transformValue(i -&gt; i + 44);
 *     strRefArgument.setValue("Hello " + strRefArgument.getValue());
 *     return "Return string";
 * }
 *
 * Ref&lt;Integer&gt; number = Ref.of(1);
 * Ref&lt;String&gt; str = Ref.of("Java");
 * String result = method(number, str);
 * System.out.println(number.getValue()); // Output: 45
 * System.out.println(str.getValue()); // Output: Hello Java
 * System.out.println(result); // Output: Return string
 * </pre>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 */
public final class Ref<T> {

    @Nullable
    private T value;

    private Ref(@Nullable T value) {
        this.value = value;
    }

    /**
     * 创建对象引用
     *
     * @param <T>   引用的类型
     * @param value 引用的对象
     * @return {@code Ref} 对象
     */
    public static <T> Ref<T> of(@Nullable T value) {
        return new Ref<>(value);
    }

    /**
     * 创建空引用
     *
     * @param <T>   引用的类型
     * @return 空引用
     */
    public static <T> Ref<T> empty() {
        return new Ref<>(null);
    }

    /**
     * 获取引用的对象
     *
     * @return 引用的对象
     */
    @Nullable
    public T getValue() {
        return value;
    }

    /**
     * 设置引用的对象
     *
     * @param value 要引用的对象
     */
    public void setValue(@Nullable T value) {
        this.value = value;
    }

    /**
     * 使用 {@link UnaryOperator} 修改 {@code Ref} 内部引用的对象
     *
     * @param operator 修改逻辑
     */
    public void transformValue(UnaryOperator<T> operator) {
        this.value = operator.apply(this.value);
    }

    /**
     * 使用 {@link Function} 修改所引用的对象，返回新的 {@code Ref}
     *
     * @param <R>      结果的引用类型
     * @param function 修改逻辑
     * @return 修改后的对象的引用
     */
    public <R> Ref<R> transform(Function<? super T, R> function) {
        return Ref.of(function.apply(this.value));
    }

    /**
     * 使用 {@link Predicate} 检查引用的对象
     *
     * @param predicate 判断逻辑
     * @return 判断结果
     */
    public boolean checkValue(Predicate<? super T> predicate) {
        return predicate.test(this.value);
    }

    /**
     * 将引用的对象作为入参，执行 {@link Consumer} 的逻辑
     *
     * @param consumer 要执行的逻辑
     */
    public void execute(Consumer<? super T> consumer) {
        consumer.accept(value);
    }

    /**
     * 判断所引用的对象是否为 {@code null}
     *
     * @return 是否为 {@code null}
     */
    public boolean isNull() {
        return this.value == null;
    }


    /**
     * 判断所引用的对象是否不为 {@code null}
     *
     * @return 是否不为 {@code null}
     */
    public boolean isNotNull() {
        return this.value != null;
    }

    @Override
    public String toString() {
        return String.format("Ref[%s]", value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ref<?> other = (Ref<?>) obj;
        return Objects.equals(this.value, other.value);
    }

}
