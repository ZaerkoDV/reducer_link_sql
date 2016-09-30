package com.instinctools.reducerlink.service.support;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class TransformUtils {
    private TransformUtils() {
    }

    public static <S, D> List<D> mapList(Collection<S> collection, Function<S, D> mapper) {
        return collection.stream().map(mapper).collect(Collectors.toList());
    }

    public static Map<String, Object> toMap(Object... args) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        for (int i = 0, len = args.length; i < len - 1; i += 2) {
            result.put(String.valueOf(args[i]), args[i + 1]);
        }

        return result;
    }

    public static <S, D> D take(S src, Function<S, D> mapper) {
        return mapper.apply(src);
    }
}
