package com.algorithm.tree;

import java.util.Map;

public class Entry<K, V> implements Map.Entry<K, V> {

    public K key;
    public V value;
    public Entry<K, V> left;
    public Entry<K, V> right;
    public int height = 1;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        this.value = value;
        return value;
    }

    public Entry(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }

    public Entry(K key){
        super();
        this.key = key;
    }

    public Entry(K key, V value, Entry<K, V> left, Entry<K, V> right) {
        super();
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Entry [key=" + key + ", value=" + value + ", height="
                + height + "]";
    }
}
