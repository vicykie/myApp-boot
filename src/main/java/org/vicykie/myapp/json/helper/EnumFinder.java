package org.vicykie.myapp.json.helper;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicykie on 2016/6/13.
 */
public class EnumFinder<T extends Enum<T>, K> {
    protected Map<K, T> map = new HashMap<>();

    public EnumFinder(Class<T> clazz, EnumKeyGetter<T, K> keyGetter) {
        for (T enumValue : EnumSet.allOf(clazz)) {
            map.put(keyGetter.getKey(enumValue), enumValue);
        }
    }

    public T find(K key, T defaultValue) {
        T value = map.get(key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }
}
