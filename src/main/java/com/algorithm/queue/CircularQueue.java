package com.algorithm.queue;

/**
 * 循环队列
 */
public class CircularQueue {

    private String[] items;
    private int catacity = 0;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int catacity) {
        this.catacity = catacity;
        items = new String[catacity];
    }

    public boolean enqueue(String item){
        if((tail + 1) % catacity == head){
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % catacity;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }
        String ret = items[head];
        head = (head + 1) % catacity;
        return ret;
    }

    public void printAll() {
        if (0 == catacity) return;
        for (int i = head; i % catacity != tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.printAll();
        System.out.println();
        for(int i = 0; i < queue.catacity; i++){
            System.out.println(queue.dequeue());
        }
    }
}
