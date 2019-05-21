package com.algorithm.stack;

/**
 * 顺序栈：基于数组实现
 */
public class ArrayStack {

    private String[] items;
    private int count;
    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.items = new String[capacity];
        this.count = 0;
    }

    public boolean push(String item){
        if(count == capacity){
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    public String pop(){
        if (count == 0){
            return null;
        }
        String temp = items[count - 1];
        --count;
        return temp;
    }


    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");

        while (stack.count != 0){
            System.out.println(stack.pop());
        }
    }

}
