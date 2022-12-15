package com.autumn.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <p>
 * StringUtils
 * </p>
 *
 * @author livk
 */
@UtilityClass
public class ObjectUtils extends org.springframework.util.ObjectUtils {

    @SafeVarargs
    public <T> boolean allChecked(Predicate<T> predicate, T... ts) {
        return !ObjectUtils.isEmpty(ts) && Stream.of(ts).allMatch(predicate);
    }

    @SafeVarargs
    public <T> boolean anyChecked(Predicate<T> predicate, T... ts) {
        return !ObjectUtils.isEmpty(ts) && Stream.of(ts).anyMatch(predicate);
    }

}
