package com.algorithm.tree;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * LeetCode 173. Binary Search Tree Iterator
 *
 * 思路：递归添加进入线性集合，迭代线性集合
 *      非递归，使用左路径节点压栈
 */
public class BSTIterator {

    private Iterator<Integer> iterator;

    public BSTIterator(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        inOrder(root, list);
        iterator = list.iterator();
    }

    /** @return the next smallest number */
    public int next() {
        return iterator.next();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iterator.hasNext();
    }

    private void inOrder(TreeNode node, ArrayList<Integer> list){
        if(node != null){
            inOrder(node.left, list);
            list.add(node.value);
            inOrder(node.right, list);
        }
    }


}
