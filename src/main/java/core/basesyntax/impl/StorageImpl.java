package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private final K[] keys;
    private final V[] values;
    private int size;

    public StorageImpl() {
        this.keys = (K[]) new Object[MAX_SIZE];
        this.values = (V[]) new Object[MAX_SIZE];
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key);
        if (index != -1) {
            values[index] = value;
        } else {
            if (size < MAX_SIZE) {
                keys[size] = key;
                values[size] = value;
                size++;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (index != -1) {
            return values[index];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(K key) {
        if (key == null) {
            return getIndexNull(key);
        } else {
            return getEqualsKeys(key);
        }
    }

    private int getEqualsKeys(K key) {
        for (int i = 0; i < size; i++) {
            if (key.equals(keys[i])) {
                return i;
            }
        }
        return -1;
    }

    private int getIndexNull(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
