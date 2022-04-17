package com.george.orca.service.common;

import java.util.List;

public interface CommonService<T> {

    T get(Long id);

    T edit(T entity);

    List<T> list();

    void delete(Long id);
}
