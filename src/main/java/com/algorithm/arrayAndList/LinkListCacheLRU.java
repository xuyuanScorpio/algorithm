package com.algorithm.arrayAndList;


/**
 * LRU基于链表：
 * 思路：维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问，我们从链表头开始顺序遍历链表。
 * 1、如果此数据之前已经被缓存到链表中，遍历得到这个数据对应的节点，并将其原来的位置删除，然后在插入到链表的头部
 * 2、如果此数据没有在链表中缓存，情况一：缓存未满，将此节点直接插入链表头部。情况二：缓存已满，链表尾部节点删除，新的数
 * 据插入链表头部
 */
public class LinkListCacheLRU<E> {

    // 头节点
    public Node<E> first;

    // 节点位置
    private int pos;

    private int size = 0;

    private int capacity = 3;

    public LinkListCacheLRU() {
        this.first = null;
    }

    public LinkListCacheLRU(int capacity) {
        this.first = null;
        this.capacity = capacity;
    }

    /**
     * 插入一个头节点
     *
     * @param e
     */
    public void addFirstNode(E e) {
        boolean result = true;
        if(size == capacity){
            result = deleteLastNode();
        }
        if(result){
            final Node<E> f = first;
            final Node<E> newNode = new Node<E>(e, f);
            first = newNode;
            size++;
        }
    }

    /**
     * 根据数据查询缓存，存在返回数据，并将其移动到链表头，如果不存在，则直接插入链表头并返回
     *
     * @param e
     * @return
     */
    public E query(E e) {

        Node<E> preNode = first;
        Node<E> currentNode = first;
        if (currentNode == null) {
            throw new RuntimeException("this LinkListCacheLRU is empty!");
        }
        while (currentNode.next != null) {
            if(currentNode.item.equals(e)){
                moveToHead(preNode, currentNode);
                return e;
            }
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        addFirstNode(e);
        return e;
    }

    public void moveToHead(Node<E> preNode, Node<E> tagetNode){

        if(tagetNode == first){
            return;
        }
        preNode.next = tagetNode.next;
        tagetNode.next = first;
    }

    public boolean deleteByIndex(int index) {

        Node<E> preNode = first;
        Node<E> currentNode = first;
        pos = 0;
        if (currentNode == null) {
            throw new RuntimeException("this LinkListCacheLRU is empty!");
        }
        if (currentNode.next == null && index == 0) {
            currentNode = null;
            return true;
        }
        while (currentNode.next != null) {
            if (index == pos) {
                preNode.next = currentNode.next;
                currentNode = null;
                return true;
            }
            preNode = currentNode;
            currentNode = currentNode.next;
            pos++;
        }
        return false;
    }


    /**
     * 删除尾部节点
     *
     * @return
     */
    public boolean deleteLastNode() {

        Node<E> preNode = first;
        Node<E> currentNode = first;
        if (currentNode == null) {
            throw new RuntimeException("this LinkListCacheLRU is empty!");
        }
        if (currentNode.next == null) {
            currentNode.next = null;
            size--;
            return true;
        }
        while (currentNode.next != null) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        preNode.next = currentNode.next;
        currentNode =  null;
        size--;
        return true;
    }


    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void printList() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        LinkListCacheLRU<String> linkListCacheLRU = new LinkListCacheLRU<String>();
        linkListCacheLRU.addFirstNode("aa");
        linkListCacheLRU.addFirstNode("bb");
        linkListCacheLRU.addFirstNode("cc");
//        linkListCacheLRU.addFirstNode("dd");
        linkListCacheLRU.printList();
//        linkListCacheLRU.deleteLastNode();
        System.out.println();
        linkListCacheLRU.query("aa");
        linkListCacheLRU.printList();
        System.out.println();
        linkListCacheLRU.query("dd");
        linkListCacheLRU.printList();


//        linkListCacheLRU.deleteByIndex(1);
//        System.out.println(linkListCacheLRU.query("ee"));

    }

}
