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
    private final Collection<T> nodes;
    private final Function<T, TIdentity> identityGetter;
    private final Function<T, Optional<TIdentity>> parentIdentityGetter;
    private final BiConsumer<TSubTree, T> addChildrenMethod;

    public TreeBuilder(Collection<T> nodes, Function<T, TIdentity> identityGetter,
            Function<T, Optional<TIdentity>> parentIdentityGetter, BiConsumer<TSubTree, T> addChildren) {
        this.nodes = nodes;
        this.identityGetter = identityGetter;
        this.parentIdentityGetter = parentIdentityGetter;
        this.addChildrenMethod = addChildren;
    }

    public List<T> buildTree() {
        Map<TIdentity, T> identityNodeMap = CollectionTools.toHashMap(nodes, identityGetter);
        List<T> result = this.nodes.stream()
                .filter(node -> !this.parentIdentityGetter.apply(node).isPresent())
                .collect(Collectors.toList());
        for (T node : this.nodes) {
            Optional<TIdentity> parentIdentity = parentIdentityGetter.apply(node);
            if (parentIdentity.isPresent() && identityNodeMap.containsKey(parentIdentity.get())) {
                @SuppressWarnings("all")
                TSubTree parentNode = (TSubTree) identityNodeMap.get(parentIdentity.get());
                addChildrenMethod.accept(parentNode, node);
            }
        }
        return result;
    }
}
