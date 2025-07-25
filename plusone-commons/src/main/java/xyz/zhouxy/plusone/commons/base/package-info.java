/*
 * Copyright 2025 the original author or authors.
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

/**
 * <h2>基础组件</h2>
 *
 * <h3>1. Ref</h3>
 * <p>
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
 * void method(Ref&lt;Integer&gt; refArgument) {
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
 * String method(Ref&lt;Integer&gt; intRefArgument, Ref&lt;String&gt; strRefArgument) {
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
 * <h3>2. IWithCode</h3>
 * <p>
 * 类似于枚举这样的类型，通常需要设置固定的码值表示对应的含义。
 * 可实现 {@link IWithCode}、{@link IWithIntCode}、{@link IWithLongCode}，便于在需要的地方对这些接口的实现进行处理。
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 */
@CheckReturnValue
@ParametersAreNonnullByDefault
package xyz.zhouxy.plusone.commons.base;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.CheckReturnValue;
