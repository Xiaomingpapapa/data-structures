package com.study.tree.huffmantree.huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        List<Node> list = getNodeList(bytes);
        System.out.println(list);

        Node root = createHuffmanTree(list);
//        preOrder(root);
        getHuffmanCode(root, "", stringBuilder);
        System.out.println(huffmanCode);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("赫夫曼树为空");
            return;
        }
        root.preOrder();
    }

    /**
     * 构造 huffman 树节点列表
     * @param bytes
     * @return
     */
    public static List<Node> getNodeList(byte[] bytes) {
        // 进行字节的权重映射
        Map<Byte, Integer> weightMap = new HashMap<>();
        for (byte aByte : bytes) {
            Integer weight = weightMap.get(aByte);
            if (weight == null) {
                weightMap.put(aByte, 1);
            } else {
                weightMap.put(aByte, weight + 1);
            }
        }

        // 初始化 node
        List<Node> nodeList = new ArrayList<>();
        weightMap.forEach((k, v) -> {
            Node node = new Node(k, v);
            nodeList.add(node);
        });
        return nodeList;
    }

    /**
     * 创建 huffman 树
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static Map<Byte, String> huffmanCode = new HashMap<>();
    public static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 创建 huffman 编码表
     * @param node huffman 树节点
     * @param code 路径编码，向左为 1，向右为 2
     * @param stringBuilder 用于拼接叶子节点的编码路径
     * @return
     */
    public static void getHuffmanCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node.getData() == null) {
            // 如果不是叶子节点，则继续向下遍历
            getHuffmanCode(node.getLeft(), "0", stringBuilder1);
            getHuffmanCode(node.getRight(), "1", stringBuilder1);
        } else {
            // 是叶子节点则直接存储其对应的 huffman 编码
            huffmanCode.put(node.getData(), stringBuilder1.toString());
        }
    }
}

class Node implements Comparable<Node> {
    private Byte data;
    //权重
    private Integer weight;
    private Node left;
    private Node right;

    public Node(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
    public int compareTo(Node o) {
        return this.weight - o.getWeight();
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
