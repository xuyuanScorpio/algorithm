package com.algorithm.queue;


public class DynamicArrayQueue {

    private String[] items;
    private int catacity = 0;
    private int head = 0;
    private int tail = 0;

    public DynamicArrayQueue(int catacity) {
        this.catacity = catacity;
        this.items = new String[catacity];
    }

    public boolean enqueue(String item){
        if(tail == catacity){
            if (head == 0){
                return false;
            }

            for(int i = head; i < tail; ++i){
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
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

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DynamicArrayQueue queue = new DynamicArrayQueue(3);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        queue.printAll();
    }

}
