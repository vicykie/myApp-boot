package org.vicykie.myapp.json.helper;

/**
 * Created by vicykie on 2016/6/13.
 */
public interface EnumKeyGetter<T extends Enum<T>, K> {
    K getKey(T enumValue);
}
