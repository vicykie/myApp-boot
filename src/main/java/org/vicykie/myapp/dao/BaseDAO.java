package org.vicykie.myapp.dao;

import java.io.Serializable;

/**
 * Created by vicykie on 2016/6/6.
 */
public interface BaseDAO<T> {
    T getObjectById(Serializable id);
}
