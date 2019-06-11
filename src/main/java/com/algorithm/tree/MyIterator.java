package com.algorithm.tree;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

public class MyIterator<K, V> implements Iterator<Entry<K, V>> {

    private Stack<Entry<K, V>> stack;

    public MyIterator(Entry<K, V> root){
        super();
        stack = new Stack<Entry<K, V>>();
        addLeftPath(root);
    }

    private void addLeftPath(Entry<K, V> entry){
        while(entry != null){
            stack.push(entry);
            entry = entry.left;
        }
    }

    public boolean hasNext() {
        return stack.isEmpty()? false : true;
    }

    public Entry<K, V> next() {
        Entry<K, V> entry = stack.pop();
        addLeftPath(entry.right);
        return entry;
    }

    public void remove() {
        throw new ConcurrentModificationException("Can not remove!");
    }


}
