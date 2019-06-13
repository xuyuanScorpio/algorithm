package com.algorithm.tree;

import com.algorithm.tree.Entry;
import com.algorithm.tree.Map;
import com.algorithm.tree.MyIterator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

public class TestBST {

    private Random random = new Random();
    private final int MAX1 = 16;

    @Test
    public void testPutAndItr(){
        Map<Integer, String> map = new Map<Integer, String>();
        for(int i = 0; i < MAX1; i++){
            map.put(random.nextInt(MAX1), random.nextInt(MAX1) + "");
        }
        MyIterator iterator = map.iterator();
        System.out.println(map.getSize());
        while(iterator.hasNext()){
            System.out.println(iterator.next().key + " ");
        }
        System.out.println();

    }

    private final int MAX2=65535;
    @Test
    public void testPutAndItrWithJDK(){
        Map<Integer, String> map1=new Map<Integer, String>();
        TreeMap<Integer, String> map2=new TreeMap<Integer, String>();
        for(int i=0;i<MAX2;i++){
            int key=random.nextInt(MAX2);
            String value=random.nextInt(MAX2)+"";
            map1.put(key, value);
            map2.put(key, value);
        }
        Assert.assertTrue(map1.getSize()==map2.size());
        System.out.println(map1.getSize());
        Iterator<Entry<Integer, String>> it1=map1.iterator();
        Iterator<java.util.Map.Entry<Integer, String>> it2=map2.entrySet().iterator();
        while(it1.hasNext()&&it2.hasNext()){
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }
        Assert.assertTrue(!it1.hasNext()&&!it2.hasNext());
    }

    @Test
    public void testQuery(){
        Map<Integer, String> map = new Map<Integer, String>();
        map.put(4, "a");
        map.put(2, "b");
        map.put(6, "c");
        map.put(2, "d");
        map.put(1, "a");
        map.put(3, "d");
        map.put(5, "e");
        map.put(1, "f");

        Assert.assertTrue(map.get(4).equals("a"));
        Assert.assertTrue(map.get(1).equals("f"));
        Assert.assertTrue(map.get(7) == null);
        Assert.assertTrue(map.containsKey(6));
        Assert.assertTrue(!map.containsKey(-1));
        Assert.assertTrue(map.containsValue("d"));
        Assert.assertTrue(!map.containsValue("xxx"));
    }

    @Test
    public void testQueryWithJDK(){
        Map<Integer, String> map1=new Map<Integer, String>();
        TreeMap<Integer, String> map2=new TreeMap<Integer, String>();
        for(int i=0;i<MAX2;i++){
            int key=random.nextInt(MAX2);
            String value=random.nextInt(MAX2)+"";
            map1.put(key, value);
            map2.put(key, value);
        }
        for(int i = 0; i < MAX2; i++){
            int key = random.nextInt(MAX2);
            Assert.assertTrue(map1.containsKey(key)==map2.containsKey(key));
            if(map1.get(key) == null){
                Assert.assertTrue(map2.get(key) == null);
            }else {
                Assert.assertTrue(map1.get(key).equals(map2.get(key)));
            }
        }
        for(int i = 0; i < 255; i++){
            String value = random.nextInt(MAX2) + "";
            Assert.assertTrue(map1.containsValue(value) == map2.containsValue(value));
        }
    }

    @Test
    public void testRemoveCase1(){
        Map<Integer, String> map = new Map<Integer, String>();
        int[] array = {5, 2, 6, 1, 4, 7, 3};
        for(int key : array){
            map.put(key, key + "");
        }
        System.out.println(map.remove(1));
        map.levelOrder();
        Iterator<Entry<Integer, String>> iterator = map.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    @Test
    public void testRemoveCase2(){
        Map<Integer, String> map = new Map<Integer, String>();
        int[] array = {5, 2, 6, 1, 4, 7, 3};
        for(int key : array){
            map.put(key, key + "");
        }
        System.out.println(map.remove(4));
        map.levelOrder();
        Iterator<Entry<Integer, String>> iterator = map.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    @Test
    public void testRemoveCase3(){
        Map<Integer, String> map = new Map<Integer, String>();
        int[] array = {5, 2, 6, 1, 4, 7, 3};
        for(int key : array){
            map.put(key, key + "");
        }
        System.out.println(map.remove(2));
        map.levelOrder();
        Iterator<Entry<Integer, String>> iterator = map.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    @Test
    public void testRemoveWithJDK(){
        Map<Integer, String> map1=new Map<Integer, String>();
        TreeMap<Integer, String> map2=new TreeMap<Integer, String>();
        for(int i=0;i<MAX2;i++){
            int key=random.nextInt(MAX2);
            String value=random.nextInt(MAX2)+"";
            map1.put(key, value);
            map2.put(key, value);
        }
        System.out.println(map1.getSize());
        for(int i=0;i<MAX2>>>1;i++){
            int key=random.nextInt(MAX2);
            if(map1.containsKey(key)){
                Assert.assertTrue(map1.remove(key).equals(map2.remove(key)));
            }else{
                Assert.assertTrue(map1.remove(key)==null&&map2.remove(key)==null);
            }
        }
        System.out.println(map1.getSize());
        Assert.assertTrue(map1.getSize()==map2.size());
        Iterator<Entry<Integer, String>> it1=map1.iterator();
        Iterator<java.util.Map.Entry<Integer, String>> it2=map2.entrySet().iterator();
        while(it1.hasNext()&&it2.hasNext()){
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }
        Assert.assertTrue(!it1.hasNext()&&!it2.hasNext());
    }

    @Test
    public void testDelete(){
        Map<Integer, String> map1=new Map<Integer, String>();
        TreeMap<Integer, String> map2=new TreeMap<Integer, String>();
        for(int i=0;i<MAX2;i++){
            int key=random.nextInt(MAX2);
            String value=random.nextInt(MAX2)+"";
            map1.put(key, value);
            map2.put(key, value);
        }
        for(int i=0;i<MAX2>>1;i++){
            int key=random.nextInt(MAX2);
            map1.remove(key);
            map2.remove(key);
        }
        map1.checkBalance();
        Assert.assertTrue(map1.getSize()==map2.size());
        Iterator<Entry<Integer, String>> it1=map1.iterator();
        Iterator<java.util.Map.Entry<Integer, String>> it2=map2.entrySet().iterator();
        while(it1.hasNext()&&it2.hasNext()){
            Assert.assertTrue(it1.next().getKey().equals(it2.next().getKey()));
        }
        Assert.assertTrue(!it1.hasNext() && !it2.hasNext());
    }

}
