package com.instinctools.reducerlink.service.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ObjUtils {
    private ObjUtils() {
    }

    public static boolean asBoolean(Object value) {
        if (value == null) {
            return false;
        } else {
            return Boolean.valueOf(String.valueOf(value));
        }
    }

    public static Integer asInteger(Object value) {
        return asInteger(value, 0);
    }

    public static Integer asInteger(Object value, Integer def) {
        if (value == null) {
            return def;
        } if (value instanceof Number) {
            return ((Number)value).intValue();
        } else {
            try {
                return Integer.valueOf(String.valueOf(value));
            } catch (NumberFormatException ex) {
                return def;
            }
        }
    }

    public static Float asFloat(Object value) {
        return asFloat(value, 0f);
    }

    public static Float asFloat(Object value, Float def) {
        if (value == null) {
            return def;
        } if (value instanceof Number) {
            return ((Number)value).floatValue();
        } else {
            try {
                return Float.valueOf(String.valueOf(value));
            } catch (NumberFormatException ex) {
                return def;
            }
        }
    }

    public static Double asDouble(Object value) {
        return asDouble(value, 0.0);
    }

    public static Double asDouble(Object value, Double def) {
        if (value instanceof Number) {
            return ((Number)value).doubleValue();
        } else {
            try {
                return Double.valueOf(String.valueOf(value));
            } catch (NumberFormatException ex) {
                return Double.valueOf(def);
            }
        }
    }

    public static Object asObject(Object node, String key) {
        if (node instanceof Map) {
            return ((Map)node).get(key);
        } else {
            return null;
        }
    }

    public static Integer asInteger(Object node, String key) {
        return asInteger(node, key, 0);
    }

    public static Integer asInteger(Object node, String key, Integer def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Number) {
                return ((Number)value).intValue();
            } else {
                try {
                    return Integer.valueOf(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    return def;
                }
            }
        } else {
            return def;
        }
    }

    public static Long asLong(Object node, String key) {
        return asLong(node, key, 0L);
    }

    public static Long asLong(Object node, String key, Long def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Number) {
                return ((Number)value).longValue();
            } else {
                try {
                    return Long.valueOf(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    return def;
                }
            }
        } else {
            return def;
        }
    }

    public static Float asFloat(Object node, String key) {
        return asFloat(node, key, 0f);
    }

    public static Float asFloat(Object node, String key, Float def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Number) {
                return ((Number)value).floatValue();
            } else {
                try {
                    return Float.valueOf(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    return def;
                }
            }
        } else {
            return def;
        }
    }

    public static Double asDouble(Object node, String key) {
        return asDouble(node, key, 0.0);
    }

    public static Double asDouble(Object node, String key, Double def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Number) {
                return ((Number)value).doubleValue();
            } else {
                try {
                    return Double.valueOf(String.valueOf(value));
                } catch (NumberFormatException ex) {
                    return def;
                }
            }
        } else {
            return def;
        }
    }

    public static Boolean asBoolean(Object node, String key) {
        return asBoolean(node, key, false);
    }

    public static Boolean asBoolean(Object node, String key, Boolean def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Boolean) {
                return ((Boolean)value).booleanValue();
            } else {
                return Boolean.valueOf(String.valueOf(value));
            }
        } else {
            return def;
        }
    }

    public static String asString(Object node, String key) {
        return asString(node, key, "");
    }

    public static String asString(Object node, String key, String def) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            return String.valueOf(value);
        } else {
            return def;
        }
    }

    public static Map<?, ?> asMap(Object node) {
        if (node instanceof Map) {
            return (Map<?, ?>)node;
        } else {
            return new HashMap<String, Object>();
        }
    }

    public static Map<?, ?> asMap(Object node, String key) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof Map) {
                return (Map<?, ?>)value;
            } else {
                return new HashMap<String, Object>();
            }
        } else {
            return new HashMap<String, Object>();
        }
    }

    public static List<?> asList(Object node) {
        if (node instanceof List) {
            return (List<?>)node;
        } else {
            return new ArrayList<Object>();
        }
    }

    public static List<?> asList(Object node, String key) {
        Object value;

        if ((node instanceof Map) && ((value = ((Map)node).get(key)) != null)) {
            if (value instanceof List) {
                return (List<?>)value;
            } else {
                return new ArrayList<Object>();
            }
        } else {
            return new ArrayList<Object>();
        }
    }

    public static Object asListItem(Object node, int idx) {
        List<?> list = asList(node);
        return (idx >= 0 && idx < list.size() ? list.get(idx) : null);
    }

    public static Object asListItem(Object node, String key, int idx) {
        List<?> list = asList(node, key);
        return (idx >= 0 && idx < list.size() ? list.get(idx) : null);
    }

    public static boolean hasKey(Object node, String key) {
        return ((node instanceof Map) && ((Map)node).containsKey(key));
    }

    public static Object defObject(Object obj, Object def) {
        return (obj == null ? def : obj);
    }

    public static String defString(String obj, String def) {
        return (obj == null ? def : obj);
    }

    public static Integer defInteger(Integer obj, Integer def) {
        return (obj == null ? def : obj);
    }
}