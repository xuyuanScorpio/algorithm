package com.algorithm.binarySearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetCode 226  反转一颗二叉树
 *
 * 输入
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 思路：前序、中序、后序、层序
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {

        if(null != root){

            TreeNode tempNode = root.left;
            root.left = root.right;
            root.right = tempNode;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }else{
            return null;
        }
    }

    /**
     * 不使用递归解法
     * @param root
     * @return
     */
    public TreeNode invertTree_bfs(TreeNode root) {

        if(root == null){
            return null;
        }else {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                TreeNode tempNode = node.left;
                node.left = node.right;
                node.right = tempNode;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            return root;
        }

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
