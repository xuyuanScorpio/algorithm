package com.algorithm.hashTable;

/**
 * 手写一个简单散列表
 */
public class SimpleHashTable {

    private int DEFAULT_CATACITY = 100;

    private Entry[] table = new Entry[DEFAULT_CATACITY];

    public void put(String key, String value){

        Entry entry = new Entry(key, value);
        int hashResult = hash(key);
        table[hashResult] = entry;

    }

    public String get(String key){

        int hashResult = hash(key);

        return table[hashResult].getValue();
    }

    public int hash(String key){


        return getHashResult(key.hashCode());
    }

    private int getHashResult(int x){

        if(x > 0 && x < 100){
            return x;
        }else {
            return getHashResult((int) Math.sqrt(x));
        }

    }

    class Entry{

        private String key;
        private String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        String key = "张春阳";
        String value = "沙雕";
        String key1 = "徐渊";
        String value1 = "傻狍子";
        SimpleHashTable table = new SimpleHashTable();
        table.put(key, value);
        table.put(key1, value1);

        System.out.println(table.get(key));
        System.out.println(table.get(key1));

    }






}
