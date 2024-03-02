package xyz.zhouxy.plusone.commons.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import xyz.zhouxy.plusone.commons.collection.CollectionTools;

public class TreeBuilder<T, TSubTree extends T, TIdentity> {
    private final Function<T, TIdentity> identityGetter;
    private final Function<T, Optional<TIdentity>> parentIdentityGetter;
    private final BiConsumer<TSubTree, T> addChildMethod;

    public TreeBuilder(Function<T, TIdentity> identityGetter, Function<T, Optional<TIdentity>> parentIdentityGetter,
            BiConsumer<TSubTree, T> addChild) {
        this.identityGetter = identityGetter;
        this.parentIdentityGetter = parentIdentityGetter;
        this.addChildMethod = addChild;
    }

    public List<T> buildTree(Collection<T> nodes) {
        Map<TIdentity, T> identityNodeMap = CollectionTools.toHashMap(nodes, identityGetter);
        List<T> result = nodes.stream()
                .filter(node -> !this.parentIdentityGetter.apply(node).isPresent())
                .collect(Collectors.toList());
        nodes.forEach(node -> parentIdentityGetter.apply(node).ifPresent(parentIdentity -> {
            if (identityNodeMap.containsKey(parentIdentity)) {
                @SuppressWarnings("unchecked")
                TSubTree parentNode = (TSubTree) identityNodeMap.get(parentIdentity);
                addChildMethod.accept(parentNode, node);
            }
        }));
        return result;
    }
}
