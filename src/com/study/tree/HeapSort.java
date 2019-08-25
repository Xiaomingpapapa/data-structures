package com.study.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {


        //初始化一个 80000 个元素的数组
        int[] array = new int[80000];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 8000000);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beforeSortTime = simpleDateFormat.format(date);
        System.out.println("排序前时间为：" + beforeSortTime);

        headSort(array);

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String afterSortTime = simpleDateFormat1.format(date1);
        System.out.println("排序前时间为：" + afterSortTime);
    }

    public static  void headSort(int[] arr) {
        // 首先调整堆的顺序，从最后一个父节点开始调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 每次将堆顶元素交换到该堆末尾（数组最后），并将其从堆中排出
        for (int i = arr.length - 1; i > 0; i--) {
            // 交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 交换后重新对堆进行调整
            adjustHeap(arr, 0, i);
        }

//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组调整成一个大顶堆
     * @param arr 待排序数组
     * @param index 非叶子节点在数组中的索引
     * @param length 对多少个元素进行排序处理 逐渐减少
     */
    public static void adjustHeap(int[] arr, int index, int length) {
        int temp = arr[index];

        for (int k = 2 * index + 1; k < length; k = k * 2 + 1) {
            // 选出叶子节点中较大的数
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果叶子节点比父节点大，则进行交换
            if (arr[k] > temp) {
                arr[index] = arr[k];
                index = k;
            } else {
                // 说明无需调整，直接退出循环，进行下一个非叶子节点的调整
                break;
            }
        }
        // 将最初的 index 值放到调整后的位置
        arr[index] = temp;
    }


}
