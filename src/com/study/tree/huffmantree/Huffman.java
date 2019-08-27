package com.study.tree.huffmantree;

import java.util.*;

/**
 * @author 33053
 * @create 2019-08-27 08:30
 * <赫夫曼树构建>
 */
public class Huffman {
    public static void main(String[] args) {
        int[] arr = new int[]{13, 7, 8, 3, 29, 6, 1};
        Node node = createHuffmanTree(arr);
        node.preOrder();
    }

    /**
     * 构建赫夫曼树
     * @param arr 原始数组
     * @return 返回树的 root 节点
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            Node node = new Node(i);
            nodes.add(node);
        }
        // 开始构建赫夫曼树
        // 直到集合中只剩下一个 root 节点
        while (nodes.size() > 1) {
            // 首先对集合进行排序
            Collections.sort(nodes);
            // 选取集合最前面两个元素构建父节点
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.getValue() + right.getValue());
            parent.setLeft(left);
            parent.setRight(right);
            // 将前两个节点移除，注意这边使用 remove(object) 这个方法，如果使用 remove(int index) 这个方法到了最后一次循环会出现数组越界问题（因为集合中只剩下一个元素）
            nodes.remove(left);
            nodes.remove(right);
            // 将父节点加入
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        // 将节点按照从小到达的顺序排列
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}