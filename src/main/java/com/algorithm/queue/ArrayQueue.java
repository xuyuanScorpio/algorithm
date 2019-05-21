package com.algorithm.queue;

public class ArrayQueue {

    private String[] items;
    private int catacity = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int catacity){
        items = new String[catacity];
        this.catacity = catacity;
    }

    public boolean enqueue(String item){
        if(tail == catacity){
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        for(int i = 0; i < queue.catacity; i++){
            System.out.println(queue.dequeue());
        }
    }

}
