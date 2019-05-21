package com.algorithm.queue;

public class SimpleLinkedListQueue<T> {

    private Node head = null;
    private Node tail = null;


    public void enqueue(T data){
        if(tail == null){
            Node newNode = new Node(data, null);
            head = newNode;
            tail = newNode;
        }else {
            tail.next = new Node(tail, null);
            tail = tail.next;
        }
    }

    public <T>T dequeue(){
        if(head == null){
            return null;
        }
        T data = (T) head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return data;
    }

    public void printAll(){
        Node currentNode = head;
        while (currentNode != null){
            System.out.println(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    private class Node<T>{
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }
    }
}
