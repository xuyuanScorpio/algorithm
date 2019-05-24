package com.algorithm.skipList;

import java.util.Random;

/**
 * 跳表的实现
 */
public class SkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();

    private Random random = new Random();

    public Node find(int value){
        Node current = head;
        for(int i = levelCount - 1; i >= 0; --i){
            while (current.forwords[i] != null && current.forwords[i].data < value){
                current = current.forwords[i];
            }
        }

        if(current.forwords[0] != null && current.forwords[0].data == value){
            return current.forwords[0];
        }else {
            return null;
        }
    }

    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for(int i = 0; i < level; ++i){
            update[i] = head;
        }

        Node current = head;
        for(int i = level - 1; i >= 0; --i){
            while(current.forwords[i] != null && current.forwords[i].data < value){
                current = current.forwords[i];
            }
            update[i] = current;
        }

        for(int i = 0; i < level; i++){
            newNode.forwords[i] = update[i].forwords[i];
            update[i].forwords[i] = newNode;
        }

        if(levelCount < level){
            levelCount = level;
        }
    }

    public void delete(int value){
        Node[] update = new Node[levelCount];
        Node current = head;
        for(int i = levelCount - 1; i >= 0; --i){
            while(current.forwords[i] != null && current.forwords[i].data < value){
                current = current.forwords[i];
            }
            update[i] = current;
        }

        if(current.forwords[0] != null && current.forwords[0].data == value){
            for(int i = levelCount - 1; i >= 0; --i){
                if(update[i].forwords[i] != null && update[i].forwords[i].data == value){
                    update[i].forwords[i] = update[i].forwords[i].forwords[i];
                }
            }
        }
    }


    private int randomLevel(){
        int level = 1;
        for(int i = 1; i < MAX_LEVEL; ++i){
            if(random.nextInt() % 2 == 1){
                level++;
            }
        }
        return level;
    }

    public void printAll(){
        Node current = head;
        while (current.forwords[0] != null){
            System.out.println(current.forwords[0] + " ");
            current = current.forwords[0];
        }
        System.out.println();
    }

    public class Node{
        private int data = -1;
        private Node forwords[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    public static void main(String[] args) {
//        SkipList skipList = new SkipList();
//        for(int i = 0; i < 20; i++null){
//            System.out.println(skipList.randomLevel());
//        }


    }
}
