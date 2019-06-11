package com.algorithm.tree;

public class TreeNode {

    public int value;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        value = x;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        super();
        this.value = val;
        this.left = left;
        this.right = right;
    }
    public TreeNode() {
        super();
    }

}
