package com.study.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, -1, 4, 2};
        insertSort(array);

    }

    public static void insertSort(int[] arr) {
        int insertValue = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //假设第一个元素是有序的，排序索引从第二个元素开始
            insertValue = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            System.out.printf("第%d次排序之后\n", i);
            System.out.println(Arrays.toString(arr));
        }
    }
}
