package com.algorithm.tree;


/**
 * 二叉查找树
 */
public class BinarySearchTree {

    private Node tree;

    public Node find(int data){
        Node node = tree;
        while (node != null){
            if(data < node.data){
                node = node.leftChild;
            }else if(data > node.data){
                node = node.rightChild;
            }else {
                return node;
            }
        }
        return null;
    }

    public void insert(int data){
        if(tree == null){
            tree = new Node(data);
            return;
        }
        Node node = tree;
        while (node != null){
            if(data > node.data){
                if(node.rightChild == null){
                    node.rightChild = new Node(data);
                    return;
                }
                node = node.rightChild;
            }else {
                if(node.leftChild == null){
                    node.leftChild = new Node(data);
                    return;
                }
                node = node.leftChild;
            }
        }
    }

    public void delete(int data){
        Node currentNode = tree;
        Node parentNode = null;
        while(currentNode != null && currentNode.data != data){
            parentNode = currentNode;
            if(data > currentNode.data){
                currentNode = currentNode.rightChild;
            }else {
                currentNode = currentNode.leftChild;
            }
        }
        if(currentNode == null){
            return;
        }
        // 删除的节点有两个子节点
        // 找到待删除节点的右子树的最小节点
        if(currentNode.leftChild != null && currentNode.rightChild != null){
            Node minNode = currentNode.rightChild;
            Node minParentNode = currentNode;
            while(minNode.leftChild != null){
                minParentNode = minNode;
                minNode = minNode.leftChild;
            }
            currentNode.data = minNode.data;
            currentNode = minNode;
            parentNode = minParentNode;
        }
        // 删除的节点是叶子节点或者仅有一个子节点
        Node child;
        if(currentNode.leftChild != null){
            child = currentNode.leftChild;
        }else if (currentNode.rightChild != null){
            child = currentNode.rightChild;
        }else {
            child = null;
        }

        if(parentNode == null){
            tree = child;
        }else if(parentNode.leftChild == currentNode){
            parentNode.leftChild = child;
        }else {
            parentNode.rightChild = child;
        }

    }

    public Node findMin(){
        if(tree == null){
            return null;
        }
        Node currentNode = tree;
        while(currentNode.leftChild != null){
            currentNode = currentNode.leftChild;
        }
        return currentNode;
    }

    public Node findMax(){

        if(tree == null){
            return null;
        }
        Node currentNode = tree;
        while(currentNode.rightChild != null){
            currentNode = currentNode.rightChild;
        }
        return currentNode;
    }

    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.leftChild);
        System.out.print(node.getData() + "  ");
        inOrder(node.rightChild);

    }

    class Node{

        private int data;
        private Node leftChild;
        private Node rightChild;

        public Node(int data){
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(33);
        binarySearchTree.insert(16);
        binarySearchTree.insert(50);
        binarySearchTree.insert(13);
        binarySearchTree.insert(18);
        binarySearchTree.insert(34);
        binarySearchTree.insert(58);

        binarySearchTree.insert(15);
        binarySearchTree.insert(17);
        binarySearchTree.insert(25);
        binarySearchTree.insert(51);
        binarySearchTree.insert(66);
        binarySearchTree.insert(19);
        binarySearchTree.insert(27);
        binarySearchTree.insert(55);

        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("删除55");
        binarySearchTree.delete(55);
        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("删除13");
        binarySearchTree.delete(13);
        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("删除18");
        binarySearchTree.delete(18);
        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("插入52");
        binarySearchTree.insert(52);
        binarySearchTree.inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("最小：" + binarySearchTree.findMin().data);
        System.out.println("最大：" + binarySearchTree.findMax().data);

        System.out.println("查找27：" + binarySearchTree.find(27).data);


    }
}
