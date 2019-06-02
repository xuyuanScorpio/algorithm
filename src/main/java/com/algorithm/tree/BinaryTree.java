package com.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 手写一个二叉树
 * 分别实现 深度（前序、中序、后序）、广度（按层）遍历一颗二叉树
 * 实现 计算 一颗树的 深度  层数
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.init();
    }

    /**
     * 初始化一颗二叉树
     *         A
     *       /   \
     *      B    C
     *    /  \  / \
     *   D   E F  G
     */
    private void init(){
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeB = new Node("B", nodeD, nodeE);
        Node nodeC = new Node("C", nodeF, nodeG);
        Node nodeA = new Node("A", nodeB, nodeC);
        this.root = nodeA;
    }

    public void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.getData() + "  ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);

    }

    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.leftChild);
        System.out.print(node.getData() + "  ");
        inOrder(node.rightChild);

    }

    public void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.print(node.getData() + "  ");
    }

    /**
     * 广度优先遍历，按层遍历
     * 思路：
     *
     * @param node
     */
    public List<List> layerOrder(Node node){

        List<List> results = new ArrayList<List>();

        if(node == null){
            return results;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(node);
        while(!queue.isEmpty()){
            List list = new ArrayList();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node currentNode = queue.poll();
                list.add(currentNode.data);
                if(currentNode.leftChild != null){
                    queue.offer(currentNode.leftChild);
                }
                if(currentNode.rightChild != null){
                    queue.offer(currentNode.rightChild);
                }
            }
            results.add(list);
        }
        return results;
    }

    public int getTreeDepth(Node node){
        if (node == null){
            return 0;
        }
        int leftDepth = getTreeDepth(node.leftChild) + 1;
        int rightDepth = getTreeDepth(node.rightChild) + 1;
        return Math.max(leftDepth, rightDepth);

    }

    public void printResults(List<List> results){

        if(results.size() == 0){
            return;
        }
        for(int i = 0; i < results.size(); i++){
            for(int j = 0; j < results.get(i).size(); j++){
                System.out.print(results.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    class Node<T>{
        private T data;
        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node<T> leftChild, Node<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }
    }

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        System.out.println("========前序遍历=======");
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        System.out.println("========中序遍历=======");
        binaryTree.inOrder(binaryTree.root);
        System.out.println();
        System.out.println("========后序遍历=======");
        binaryTree.postOrder(binaryTree.root);
        System.out.println();
        System.out.println("========按层遍历=======");
        binaryTree.printResults(binaryTree.layerOrder(binaryTree.root));
        System.out.println();
        System.out.println("========计算树的深度=======");
        System.out.println(binaryTree.getTreeDepth(binaryTree.root));

    }

}
