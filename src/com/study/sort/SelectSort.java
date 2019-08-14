package com.study.sort;

import java.util.Arrays;

/**
 * @author 33053
 * @create 2019-08-14 08:36
 * <>
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, -1, 5, 4, 6, 3, 0};
        selectSort(array);

    }


    public static void selectSort(int[] array) {
        //要点，每次都找一个最大或最小的元素排在数组前面位置
        for (int i= 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            int tempIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    tempIndex = j;
                }
            }

            if (minIndex != tempIndex) {
                array[tempIndex] = array[minIndex];
                array[minIndex] = min;
            }
        }

        System.out.println("排序后的数组");
        System.out.println(Arrays.toString(array));
    }
}