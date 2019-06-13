package com.algorithm.stack;

/**
 * 基于链表实现栈
 */
public class LinkedListStack<T> {

    private Node top = null;

    public void push(T data){
        Node newNode = new Node(data, null);
        if(top == null){
            top = newNode;
        }else {
            newNode.next = top;
            top = newNode;
        }
    }

    public <T>T  pop(){
        if(top == null){
            return null;
        }
        T data = (T) top.data;
        top = top.next;
        return data;
    }

    public void printAll(){
        Node current = top;
        while (current != null){
            System.out.println(current.data + " ");
            current = current.next;
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

    public static void main(String[] args) {
        LinkedListStack<String> stack = new LinkedListStack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        while (stack.top != null){
            System.out.println(stack.pop().toString());
        }
    }
}
