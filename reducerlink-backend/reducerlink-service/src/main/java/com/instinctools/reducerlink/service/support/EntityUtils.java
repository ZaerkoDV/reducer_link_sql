package com.instinctools.reducerlink.service.support;

import java.util.Arrays;
import java.util.List;
import com.instinctools.reducerlink.model.BaseEntity;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static <T> T getFirst(List<T> list) {
        return ((list == null || list.isEmpty()) ? null : list.get(0));
    }

    public static <T> List<T> makeList(T item) {
        return Arrays.asList(item);
    }

    public static boolean hasId(BaseEntity<?> entity) {
        if (entity == null || entity.getId() == null) {
            return false;
        }

        if (entity.getId() instanceof Number) {
            return (((Number)entity.getId()).longValue() != 0L);
        }

        return true;
    }

    public static boolean equalsById(BaseEntity<?> lhs, BaseEntity<?> rhs) {
        Object lhsId = (lhs == null ? null : lhs.getId());
        Object rhsId = (rhs == null ? null : rhs.getId());

        if ((lhsId instanceof Number) && ((Number)lhsId).longValue() == 0L) {
            lhsId = null;
        }

        if ((rhsId instanceof Number) && ((Number)rhsId).longValue() == 0L) {
            rhsId = null;
        }

        return ((lhsId == null && rhsId == null) || (lhsId != null && rhsId != null && lhsId.equals(rhsId)));
    }
}
