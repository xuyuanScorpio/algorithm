package com.algorithm.arrayAndList;


/**
 * 1.单链表的插入、删除、查找基本操作 练习
 *
 *
 * 注意：由于只是练习，data域为方便只使用数字
 *
 */
public class LinkedListOperater {

    private Node head = null;

    public Node findByValue(int value){
        Node p = head;
        while(p != null){
            if(p.data == value){
                return p;
            }
            p = p.next;
        }
        throw new RuntimeException("缓存中没有待查询数据！");
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while(p != null){
            if(pos == index){
                return p;
            }
            p = p.next;
            ++pos;
        }
        throw new RuntimeException("缓存中没有待查询索引！");
    }

    public void insertToHead(int value){
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode){
        if(head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertTail(int value){
        Node newNode = new Node(value, null);
        if(head == null){
            head = newNode;
        }else{
            Node node = head;
            while(node.next != null){
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }
    }

    public void insertAfter(Node node, int value){
        Node newNode = new Node(value, null);
        insertAfter(node, newNode);
    }

    public void insertAfter(Node node, Node newNode){
        if (node == null){
            return;
        }
        newNode.next = node.next;
        node.next = newNode;
    }

    public void insertBefore(Node node, int value){
        Node newNode = new Node(value, null);
        insertBefore(node, newNode);
    }

    public void insertBefore(Node node, Node newNode){
        if(node == null){
            return;
        }
        if(head == node){
            insertToHead(newNode);
            return;
        }
        Node target = head;
        while(target != null){
            if(target.next == node){
                break;
            }
            target = target.next;
        }

        if(target == null){
            return;
        }
        newNode.next = node;
        target.next = newNode;
    }

    public void deleteByNode(Node node){
        if(node == null || head == null){
            return;
        }
        if(node == head){
            head = head.next;
            return;
        }
        Node target = head;
        while (target != null && target.next != node) {
            target = target.next;
        }

        if (target == null) {
            return;
        }

        target.next = target.next.next;
    }

    public void deleteByValue(int value){
        if(head == null){
            return;
        }
        Node target = head;
        Node targetPre = null;
        while(target != null && target.data != value){
            targetPre = target;
            target = target.next;
        }
        if(target == null){
            return;
        }
        if(targetPre == null){
            head = head.next;
        }else {
            targetPre.next = target.next.next;
        }
    }

    public void printAll(){
        Node node = head;
        while(node != null){
            System.out.println(node.data + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }
    }

}
