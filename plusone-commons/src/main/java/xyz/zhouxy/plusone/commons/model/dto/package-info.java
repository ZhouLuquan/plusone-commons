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
 * <h2>数据传输对象</h2>
 *
 * <h3>1. 分页</h3>
 * <p>
 * 分页组件由 {@link PagingAndSortingQueryParams} 作为入参，
 * 因为分页必须伴随着排序，不然可能出现同一个对象重复出现在不同页，有的对象不被查询到的情况，
 * 所以分页查询的入参必须包含排序条件。
 * </p>
 * <p>
 * 用户可继承 {@link PagingAndSortingQueryParams}
 * 构建自己的分页查询入参，需在构造器中调用 {@link PagingAndSortingQueryParams} 的构造器，传入一个 Map 作为白名单，
 * key 是供前端指定用于排序的属性名，value 是对应数据库中的字段名，只有在白名单中指定的属性名才允许作为排序条件。
 * </p>
 * <p>
 * {@link PagingAndSortingQueryParams} 包含三个主要的属性：
 * <ul>
 * <li>size - 每页显示的记录数</li>
 * <li>pageNum - 当前页码</li>
 * <li>orderBy - 排序条件</li>
 * </ul>
 * 其中 orderBy 是一个 List，可以指定多个排序条件，每个排序条件是一个字符串，
 * 格式为“属性名-ASC”或“属性名-DESC”，分别表示升序和降序。
 * </p>
 * <p>
 * 比如前端传入的 orderBy 为 ["name-ASC","age-DESC"]，意味着要按 name 进行升序，name 相同的情况下则按 age 进行降序。
 * </p>
 * <p>
 * 使用时调用 {@link PagingAndSortingQueryParams#buildPagingParams()} 方法获取分页参数 {@link PagingParams}。
 * </p>
 * <p>
 * 分页结果可以存放到 {@link PageResult} 中，作为出参。
 * </p>
 *
 * <h3>2. {@link UnifiedResponse}</h3>
 * <p>
 * {@link UnifiedResponse} 对返回给前端的数据进行封装，包含 code、message、data。
 * </p>
 * <p>
 * 可使用 {@link UnifiedResponses} 快速构建 {@link UnifiedResponse} 对象。
 * {@link UnifiedResponses} 默认的成功代码为 "2000000"，
 * 用户按测试类
 * <a href="http://zhouxy.xyz:3000/plusone/plusone-commons/src/branch/main/src/test/java/xyz/zhouxy/plusone/commons/model/dto/CustomUnifiedResponseFactoryTests.java">CustomUnifiedResponseFactoryTests</a>
 * 中所示范的，继承 {@link UnifiedResponses} 实现自己的工厂类，
 * 自定义 SUCCESS_CODE 和 DEFAULT_SUCCESS_MSG 和工厂方法。
 * 见 <a href="http://zhouxy.xyz:3000/plusone/plusone-commons/issues/22">issue#22</a>。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
@ParametersAreNonnullByDefault
package xyz.zhouxy.plusone.commons.model.dto;

import javax.annotation.ParametersAreNonnullByDefault;
