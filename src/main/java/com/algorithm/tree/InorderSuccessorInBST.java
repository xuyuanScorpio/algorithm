package com.algorithm.tree;

/**
 * LintCode 448 没有父节点parent，要求时间、空间复杂度小于O(logN)
 * 思路：情况1：p是最大节点，没有后继 直接返回null
 *      情况2：p有右子树，获取右子树中最小节点
 *      情况3：P没有右子树，查找节点P,向上回溯，找到第一个关键字比孩子大的父亲
 */
public class InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p){
        if(p == null){
            return null;
        }
        if(getLastEntry(root) == p){
            return null;
        }
        if(p.right != null){
            return getFirstEntry(p.right);
        }
        TreeNode parent = root;
        TreeNode temp = root;
        while(parent != null){
            if(parent == p){
                break;
            }else if(p.value < parent.value){
                temp = parent;
                parent = parent.left;
            }else {
                parent = parent.right;
            }
        }
        return temp;
    }

    private TreeNode getLastEntry(TreeNode p){
        while(p.right != null){
            p = p.right;
        }
        return p;
    }
    private TreeNode getFirstEntry(TreeNode p){
        while(p.left != null){
            p = p.left;
        }
        return p;
    }
}
