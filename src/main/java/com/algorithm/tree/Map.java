package com.algorithm.tree;

import org.junit.Assert;
import java.util.*;

public class Map<K, V> implements Iterable<Entry<K, V>> {

    private int size;
    private Entry<K, V> root;
    private Comparator<K> comparator;
    private LinkedList<Entry<K, V>> stack = new LinkedList<Entry<K, V>>();

    private int compare(K a, K b){
        if(comparator != null){
            return comparator.compare(a, b);
        }else {
            Comparable<K> comparable = (Comparable<K>) a;
            return comparable.compareTo(b);
        }
    }

    public Map() {
        super();
    }

    public Map(Comparator<K> comparator) {
        super();
        this.comparator = comparator;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V put(K key, V value){
        if(root == null){
            root = new Entry<K, V>(key, value);
            stack.push(root);
            size++;
        }else {
            Entry<K, V> entry = root;
            while(entry != null){
                stack.push(root);
                int compareResult = compare(key, entry.key);
                if(compareResult == 0){
                    entry.setValue(value);
                    break;
                }else if(compareResult < 0){
                    if(entry.left == null){
                        entry.left = new Entry<K, V>(key, value);
                        size++;
                        stack.push(entry.left);
                        break;
                    }else {
                        entry = entry.left;
                    }
                }else {
                    if(entry.right == null){
                        entry.right = new Entry<K, V>(key, value);
                        size++;
                        stack.push(entry.right);
                        break;
                    }else {
                        entry = entry.right;
                    }
                }
            }
        }
        fixAfterInsertion(key);
        return value;
    }

    public MyIterator iterator() {
        return new MyIterator<K, V>(root);
    }

    private Entry<K, V> getEntry(K key){
        Entry<K, V> entry = root;
        while(entry != null){
            int compareResult = compare(key, entry.key);
            if(compareResult == 0){
                return entry;
            }else if(compareResult < 0){
                entry = entry.left;
            }else {
                entry = entry.right;
            }
        }
        return null;
    }

    public boolean containsKey(K key){
        Entry<K, V> entry = getEntry(key);
        return entry != null;
    }

    public V get(K key){
        Entry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    public boolean containsValue(V value){
        MyIterator iterator = this.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    public Entry<K, V> getFirstEntry(Entry<K, V> entry){
        if(entry == null){
            return null;
        }
        while(entry.left != null){
            entry = entry.left;
        }
        return entry;
    }

    public Entry<K, V> getLastEntry(Entry<K, V> entry){
        if(entry == null){
            return null;
        }
        while(entry.right != null){
            entry = entry.right;
        }
        return entry;
    }

    private Entry<K, V> deleteEntry(Entry<K, V> entry, K key){
        if(entry == null){
            return null;
        }else {
            int compareResult = compare(key, entry.key);
            if(compareResult == 0){
                if(entry.left == null && entry.right == null){
                    entry = null;
                }else if(entry.left != null && entry.right == null){
                    entry = entry.left;
                }else if(entry.left == null && entry.right != null){
                    entry = entry.right;
                }else {
                    if((size & 1) == 0){
                        Entry<K, V> rightMin = getFirstEntry(entry.right);
                        entry.key = rightMin.key;
                        entry.value = rightMin.value;
                        Entry<K, V> newRight = deleteEntry(entry.right, entry.key);
                        entry.right = newRight;
                    }else {
                        Entry<K, V> leftMax = getLastEntry(entry.left);
                        entry.key = leftMax.key;
                        entry.value = leftMax.value;
                        Entry<K, V> newLeft = deleteEntry(entry.left, entry.key);
                        entry.left = newLeft;
                    }
                }
            }else if(compareResult < 0){
                Entry<K, V> newLeft = deleteEntry(entry.left, key);
                entry.left = newLeft;
            }else {
                Entry<K, V> newRight = deleteEntry(entry.right, key);
                entry.right= newRight;
            }
            entry = fixAfterDeletion(entry);
            return entry;
        }
    }

    public int getHeight(Entry<K, V> entry){
        return entry == null ? 0 : entry.height;
    }

    private Entry<K, V> rotateRight(Entry<K, V> p){
        Entry<K, V> left = p.left;
        p.left = left.right;
        left.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        left.height = Math.max(getHeight(left.left), p.height) + 1;
        return left;

    }

    private Entry<K, V> rotateLeft(Entry<K, V> p){
        Entry<K, V> right=p.right;
        p.right=right.left;
        right.left=p;
        p.height=Math.max(getHeight(p.left), getHeight(p.right))+1;
        right.height=Math.max(p.height, getHeight(right.right))+1;
        return right;
    }

    private Entry<K, V> firstLeftThenRight(Entry<K, V> p){
        p.left = rotateLeft(p);
        p = rotateRight(p);
        return p;
    }

    private Entry<K, V> firstRightThenLeft(Entry<K, V> p){
        p.right = rotateRight(p.right);
        p = rotateLeft(p);
        return p;
    }

    private void fixAfterInsertion(K key){
        Entry<K, V> p = root;
        while(!stack.isEmpty()){
            p = stack.pop();
            int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
            if(p.height > 1 && newHeight == p.height){
                stack.clear();
                return;
            }
            p.height = newHeight;
            int d = getHeight(p.left) - getHeight(p.right);
            if(Math.abs(d) <= 1){
                continue;
            }else {
                if(d == 2){
                    if(compare(key, p.left.key) < 0){
                        p = rotateRight(p);
                    }else {
                        p = firstLeftThenRight(p);
                    }
                }else {
                    if(compare(key, p.right.key)>0){
                        p = rotateLeft(p);
                    }else{
                        p = firstRightThenLeft(p);
                    }
                }
                if(!stack.isEmpty()){
                    if(compare(key, stack.peek().key) < 0){
                        stack.peek().left = p;
                    }else {
                        stack.peek().right = p;
                    }
                }
            }
        }
        root = p;
    }

    public void checkBalance(){
        postOrderCheckBalance(root);
    }

    private void postOrderCheckBalance(Entry<K, V> p){
        if(p != null){
            postOrderCheckBalance(p.left);
            postOrderCheckBalance(p.right);
            Assert.assertTrue(Math.abs(getHeight(p.left) - getHeight(p.right)) <= 1);
        }
    }

    public V remove(K key){
        Entry<K, V> entry = getEntry(key);
        if(entry == null){
            return null;
        }
        V oldValue = entry.getValue();
        root = deleteEntry(root, key);
        size--;
        return oldValue;
    }

    public void levelOrder(){
        Queue<Entry<K, V>> queue = new LinkedList<Entry<K, V>>();
        queue.offer(root);
        int preCount = 1;
        int pCount = 0;
        while(!queue.isEmpty()){
            preCount--;
            Entry<K, V> entry = queue.poll();
            System.out.println(entry + "  ");
            if(entry.left != null){
                queue.offer(entry.left);
                pCount++;
            }
            if(entry.right != null){
                queue.offer(entry.right);
                pCount++;
            }
            if(preCount == 0){
                preCount = pCount;
                pCount = 0;
                System.out.println();
            }
        }
    }

    private Entry<K, V> fixAfterDeletion(Entry<K, V> p){
        if(p == null){
            return null;
        }else {
            p.height = Math.max(getHeight(p.left), getHeight(p.right) + 1);
            int d = getHeight(p.left) - getHeight(p.right);
            if(d == 2){
                if(getHeight(p.left.left) - getHeight(p.left.right) >= 0){
                    p = rotateRight(p);
                }else {
                    p = firstLeftThenRight(p);
                }
            }else if(d == -2){
                if(getHeight(p.right.right) - getHeight(p.right.left) >= 0){
                    p = rotateLeft(p);
                }else {
                    p = firstRightThenLeft(p);
                }
            }
        }
        return p;
    }

}
