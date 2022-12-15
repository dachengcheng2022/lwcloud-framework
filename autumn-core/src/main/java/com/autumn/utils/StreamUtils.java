package com.autumn.utils;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <p>
 * StreamUtils
 * </p>
 *
 * @author livk
 */
@UtilityClass
public class StreamUtils {

    @SafeVarargs
    public <K, V> Map<K, List<V>> concat(Map<K, V>... maps) {
        if (ObjectUtils.isEmpty(maps)) {
            return Collections.emptyMap();
        }
        return Arrays.stream(maps)
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        kListEntry -> kListEntry.getValue()
                                .stream()
                                .map(Map.Entry::getValue)
                                .collect(Collectors.toList())));
    }

    @SafeVarargs
    public <T> Stream<T> concat(T[]... ts) {
        if (ObjectUtils.isEmpty(ts)) {
            return Stream.empty();
        }
        return Arrays.stream(ts).filter(Objects::nonNull).flatMap(Arrays::stream);
    }

    public <T> Predicate<T> distinct(Function<? super T, ?> function) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(function.apply(t), Boolean.TRUE) == null;
    }

    public <T> Stream<T> of(Iterator<T> iterator) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                false);
    }
}
