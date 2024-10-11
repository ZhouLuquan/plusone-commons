/*
 * Copyright 2023-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * TreeBuilder
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0
 */
public class TreeBuilder<T, TSubTree extends T, TIdentity> {
    private final Function<T, TIdentity> identityGetter;
    private final Function<T, Optional<TIdentity>> parentIdentityGetter;
    private final BiConsumer<TSubTree, T> addChildMethod;
    private final Comparator<? super T> defaultComparator;

    public TreeBuilder(Function<T, TIdentity> identityGetter, Function<T, Optional<TIdentity>> parentIdentityGetter,
            BiConsumer<TSubTree, T> addChild) {
        this(identityGetter, parentIdentityGetter, addChild, null);
    }

    public TreeBuilder(Function<T, TIdentity> identityGetter, Function<T, Optional<TIdentity>> parentIdentityGetter,
            BiConsumer<TSubTree, T> addChild, Comparator<? super T> defaultComparator) {
        this.identityGetter = identityGetter;
        this.parentIdentityGetter = parentIdentityGetter;
        this.addChildMethod = addChild;
        this.defaultComparator = defaultComparator;
    }

    /**
     * 将节点构建成树。使用 {@link #defaultComparator} 进行排序。如果 {@link #defaultComparator}
     * <p>
     * <b>注意，该方法会直接操作 nodes 列表中的节点，并没有做深拷贝，
     * 注意避免 nodes 中的元素产生变化所带来的意料之外的影响。</b>
     * 
     * @param nodes 平铺的节点列表
     */
    public List<T> buildTree(Collection<T> nodes) {
        Preconditions.checkNotNull(nodes);
        return buildTreeInternal(nodes, this.defaultComparator);
    }

    /**
     * 将节点构建成树。
     * <p>
     * <b>！！注意：该方法会直接操作 nodes 列表中的节点，并没有做深拷贝，
     * 注意避免 nodes 中的元素产生变化所带来的意料之外的影响。</b>
     * 
     * @param nodes      平铺的节点列表
     * @param comparator 用于节点的排序。
     *                   若为 {@code null}，则使用 {@link #defaultComparator}；
     *                   若 {@link #defaultComparator} 也为 {@code null}，则不排序。
     *                   <b>仅影响调用 addChild 的顺序，如果操作对象本身对应的控制了子节点的顺序，无法影响其相关逻辑。</b>
     */
    public List<T> buildTree(Collection<T> nodes, @Nullable Comparator<? super T> comparator) {
        Preconditions.checkNotNull(nodes);
        final Comparator<? super T> c = (comparator != null) ? comparator : this.defaultComparator;
        return buildTreeInternal(nodes, c);
    }

    /**
     * 将节点构建成树。
     * <p>
     * <b>注意，该方法会直接操作 nodes 列表中的节点，并没有做深拷贝，
     * 注意避免 nodes 中的元素产生变化所带来的意料之外的影响。</b>
     * 
     * @param nodes      平铺的节点列表
     * @param comparator 用于节点的排序。若为 {@code null}，则不排序
     */
    private List<T> buildTreeInternal(Collection<T> nodes, @Nullable Comparator<? super T> comparator) {
        final Collection<T> allNodes;
        if (comparator == null) {
            allNodes = nodes;
        } else {
            allNodes = nodes.stream().sorted(comparator).collect(Collectors.toList());
        }

        final Map<TIdentity, T> identityNodeMap = allNodes.stream()
                .collect(Collectors.toMap(identityGetter, Function.identity(), (n1, n2) -> n1));
        // 根节点
        final List<T> rootNodes = allNodes.stream()
                .filter(node -> !this.parentIdentityGetter.apply(node).isPresent())
                .collect(Collectors.toList());
        allNodes.forEach(node -> parentIdentityGetter.apply(node).ifPresent(parentIdentity -> {
            if (identityNodeMap.containsKey(parentIdentity)) {
                @SuppressWarnings("unchecked")
                TSubTree parentNode = (TSubTree) identityNodeMap.get(parentIdentity);
                addChildMethod.accept(parentNode, node);
            }
        }));
        return rootNodes;
    }
}
