package com.study.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{-1, -2, 4, 1, 3, 2};

        // 要点，将大的数往数组尾部移动，总共需要比较 n -1 轮，n 为元素个数，每轮比较次数都为 n - 1 - i(i 标识第几轮比较)
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println("排序后的数组");
        for (int i : array) {
            System.out.println(i);
        }

    }
}
