package com.algorithm.arrayAndList;

import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.util.HashMap;

/**
 * 使用双向链表实现缓存  版本2
 */
public class LinkListCacheLRU2<K, V> {

    private static int MAX_CACHE_SIZE = 6;
    private Entry<K, V> head;
    private Entry<K, V> tail;

    private HashMap<K, Entry<K, V>> cache;

    public LinkListCacheLRU2() {
    }

    public LinkListCacheLRU2(int cacheSize) {
        this.MAX_CACHE_SIZE = cacheSize;
        cache = new HashMap<K, Entry<K, V>>();
    }

    public void put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            if (cache.size() >= MAX_CACHE_SIZE) {
                cache.remove(tail.key);
                removeTail();
            }
            entry = new Entry<K, V>();
            entry.key = key;
            entry.value = value;
            moveToHead(entry);
            cache.put(key, entry);
        } else {
            entry.value = value;
            moveToHead(entry);
        }
    }

    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        moveToHead(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            if (entry == head) {
                Entry<K, V> next = head.next;
                head.next = null;
                head = next;
                head.pre = null;
            } else if (entry == tail) {
                Entry<K, V> previous = tail.pre;
                tail.pre = null;
                tail = previous;
                tail.next = null;
            } else {
                entry.pre.next = entry.next;
                entry.next.pre = entry.pre;
            }
            cache.remove(key);
        }
    }

    private void removeTail() {
        if (tail != null) {
            Entry<K, V> previous = tail.pre;
            if (previous == null) {
                head = null;
                tail = null;
            } else {
                tail.pre = null;
                tail = previous;
                tail.next = null;
            }
        }
    }

    private void moveToHead(Entry<K, V> entry){
        if(entry == head){
            return;
        }
        if(entry.pre != null){
            entry.pre.next = entry.next;
        }
        if(entry.next != null){
            entry.next.pre = entry.pre;
        }
        if(entry == tail){
            Entry<K, V> previous = entry.pre;
            if(previous != null){
                tail.pre = null;
                tail = previous;
                tail.next = null;
            }
        }
        if(head == null || tail == null){
            head = tail = entry;
            return;
        }
        entry.next = head;
        head.pre = entry;
        entry.pre = null;
        head = entry;
    }

    private Entry<K, V> getEntry(K key) {
        return cache.get(key);
    }

    private static class Entry<K, V> {
        Entry<K, V> pre;
        Entry<K, V> next;
        K key;
        V value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Entry<K, V> entry = head;
        while(entry != null){
            stringBuilder.append(String.format("%s:%s", entry.key, entry.value));
            entry = entry.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkListCacheLRU2<Integer, Integer> lru2 = new LinkListCacheLRU2<Integer, Integer>(5);
        lru2.put(1, 1);
        System.out.println(lru2);
        lru2.put(2, 2);
        System.out.println(lru2);
        lru2.put(3, 3);
        System.out.println(lru2);
        lru2.get(1);
        System.out.println(lru2);
        lru2.put(4, 4);
        lru2.put(5, 5);
        lru2.put(6, 6);
        System.out.println(lru2);

    }
}
