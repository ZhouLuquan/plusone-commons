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
 * <h2>注解</h2>
 *
 * <h3>
 * 1. {@link StaticFactoryMethod}
 * </h3>
 * <p>
 * 标识<b>静态工厂方法</b>。
 * 《Effective Java》的 Item1 建议考虑用静态工厂方法替换构造器，
 * 因而考虑有一个注解可以标记一下静态工厂方法，以和其它方法进行区分。
 *
 * <h3>
 * 2. {@link ReaderMethod} 和 {@link WriterMethod}
 * </h3>
 * <p>
 * 分别标识<b>读方法</b>（如 getter）或<b>写方法</b>（如 setter）。
 *
 * <p>
 * 最早是写了一个集合类，为了方便判断使用读写锁时，哪些情况下使用读锁，哪些情况下使用写锁。
 *
 * <h3>
 * 3. {@link UnsupportedOperation}
 * </h3>
 * <p>
 * 标识该方法不被支持或没有实现，将抛出 {@link UnsupportedOperationException}。
 * 为了方便在使用时，不需要点进源码，就能知道该方法没有实现。
 *
 * <h3>
 * 4. {@link Virtual}
 * </h3>
 * <p>
 * Java 非 final 的实例方法，对应 C++/C&num; 中的虚方法，允许被子类覆写。
 * {@link Virtual} 注解旨在设计父类时，强调该方法父类虽然有默认实现，但子类可以根据自己的需要覆写。
 *
 * <h3>
 * 5. {@link ValueObject}
 * </h3>
 * <p>
 * 标记一个类，表示其作为值对象，区别于 Entity。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
package xyz.zhouxy.plusone.commons.annotation;
