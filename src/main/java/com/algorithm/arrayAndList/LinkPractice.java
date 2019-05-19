package com.algorithm.arrayAndList;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.实现单链表的反转
 * 2.链表中环的检测，如果有环，如何找到环的起点，如何计算出环的长度
 * 3.删除链表倒数第N个结点
 * 4.求链表的中间结点
 * 5.回文字符串的判断
 * <p>
 * 注意：由于只是练习，data域为方便只使用数字
 */
public class LinkPractice {

    private Node head;

    public LinkPractice() {
    }

    public LinkPractice(Node head) {
        this.head = head;
    }

    /**
     * 获取链表的中间结点  使用快慢指针
     *
     * @param head 链表头结点
     * @return
     */
    public Node findMiddleNode(Node head) {

        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 单链表反转
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        if (head == null) {
            return head;
        }
        Node dummy = new Node(-1, null);
        dummy.next = head;
        Node previous = dummy.next;
        Node current = previous.next;
        while (current != null) {
            previous.next = current.next;
            current.next = dummy.next;
            dummy.next = current;
            current = previous.next;
        }
        return dummy.next;
    }

    /**
     * 检测链表中是否有环
     * 思路：使用快慢指针，如果快慢指针能相遇，说明有环
     * @param head
     * @return
     */
    public boolean checkLoop(Node head){
        if(head == null){
            return false;
        }
        Node fast = head.next;
        Node slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    /**
     * 计算环的长度
     * 思路：使用快慢指针，在相同起点开始，相遇时候所用的步数即环的长度
     * @param head
     * @return
     */
    public int getLoopLength(Node head){
        int length = 0;
        if(head == null){
            return length;
        }
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            length++;
            if(slow == fast){
                return length;
            }
        }
        return length;
    }

    /**
     * 获取环的入口
     * 思路：使用hashmap，以节点为键，节点下标为值，如果有环，会产生hash冲突，返回节点下标，
     *      即为环的入口
     * @param head
     * @return
     */
    public int getLoopOrigin(Node head){

        HashMap map = new HashMap<Node, Integer>();
        Node current = head;
        int step = 0;
        Integer temp;
        while(head != null){
            temp = (Integer) map.put(current, step++);
            if(temp != null){
                return temp;
            }
            current = current.next;
        }
        throw new RuntimeException("该链表没有环！");
    }

    /**
     * 合并2条有序链表 eg: A: 1-->3-->5-->  B: 2-->4-->6-->
     *                   Result: 1-->2-->3-->4-->5-->6-->
     * 思路：使用递归
     * @param l1
     * @param l2
     * @return
     */
    public Node mergeTwoLists(Node l1, Node l2){
        Node head;
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.data <= l2.data){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        }else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 删除倒数第N 个节点
     * 思路：使用 双指针，循环1次实现
     * @param head
     * @param n
     * @return
     */
    public Node deleteLastNth(Node head, int n){
        Node first = head;
        if (first == null){
            return first;
        }
        while(n-- != 0){
            first = first.next;
        }
        Node second = head;
        while(first.next != null){
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return head;
    }


    public void add(int value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        }
    }

    public Node add(Node head, Node newNode) {
        if (head == null) {
            return newNode;
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        return head;
    }

    public void add(int value, Node next) {
        Node newNode = new Node(value, next);
        if (head == null) {
            head = newNode;
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
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

    public void printAll(Node head) {
        Node node = head;
        while (node != null) {
            System.out.println(node.data + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(1, null);
        Node head2 = new Node(2, null);
        LinkPractice linkPractice = new LinkPractice();
        linkPractice.add(head, new Node(3, null));
        linkPractice.add(head, new Node(5, null));
        linkPractice.add(head, new Node(7, null));
        linkPractice.add(head2, new Node(4, null));
        linkPractice.add(head2, new Node(6, null));
//        linkPractice.add(6, linkPractice.findByIndex(2));
        linkPractice.printAll(head);
        System.out.println();
//        linkPractice.printAll(head2);
//        System.out.println();
        linkPractice.printAll(linkPractice.deleteLastNth(head, 2));

//        linkPractice.printAll(linkPractice.mergeTwoLists(head, head2));

//        System.out.println(linkPractice.getLoopOrigin(head));

//        System.out.println(linkPractice.getLoopLength(head));

//        System.out.println(linkPractice.findByIndex(2).data);

//        linkPractice.printAll(linkPractice.reverseList(linkPractice.head));

//        System.out.println(linkPractice.checkLoop(head));

//        System.out.println(linkPractice.findMiddleNode(head).data);
//        linkPractice.add(6);
//        System.out.println(linkPractice.findMiddleNode(head).data);
//        linkPractice.add(7);
//        System.out.println(linkPractice.findMiddleNode(head).data);




    }

}
