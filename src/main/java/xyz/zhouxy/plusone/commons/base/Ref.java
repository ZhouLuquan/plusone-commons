/*
 * Copyright 2024 the original author or authors.
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
 * <p>灵感来自于 C# 的 {@value ref} 参数修饰符。C# 允许通过以下方式，将值返回给调用端：</p>
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
 * </p>
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

    public static <T> Ref<T> of(@Nullable T value) {
        return new Ref<>(value);
    }

    public static <T> Ref<T> empty() {
        return new Ref<>(null);
    }

    @Nullable
    public T getValue() {
        return value;
    }

    public void setValue(@Nullable T value) {
        this.value = value;
    }

    public void transformValue(UnaryOperator<T> operator) {
        this.value = operator.apply(this.value);
    }

    public <R> Ref<R> transform(Function<? super T, R> function) {
        return Ref.of(function.apply(this.value));
    }

    public boolean checkValue(Predicate<? super T> predicate) {
        return predicate.test(this.value);
    }

    public void execute(Consumer<? super T> consumer) {
        consumer.accept(value);
    }

    public boolean isNull() {
        return this.value == null;
    }

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
