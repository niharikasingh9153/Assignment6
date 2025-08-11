package com.hdfc.policy.repository;

import java.util.*;

public class Repository<T> {
    private Map<String, T> store = new HashMap<>();

    public void add(String id, T obj) {
        store.put(id, obj);
    }

    public T get(String id) {
        return store.get(id);
    }

    public void remove(String id) {
        store.remove(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(store.values());
    }

    public Map<String, T> getStore() {
        return store;
    }
}
