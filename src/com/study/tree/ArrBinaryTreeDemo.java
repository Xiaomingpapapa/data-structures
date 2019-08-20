package com.study.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        //声明一个完美二叉树的顺序存储数组，根节点对应数组下标为 0
        //假设父节点对应的下标 i，则左节点对应下标为 2 * i + 1，右节点对应下标而 2 * i + 2
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 前序遍历
     * @param index 遍历的起始节点
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空");
        }
        System.out.println(arr[index]);
        //输出左节点
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //输出右节点
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
