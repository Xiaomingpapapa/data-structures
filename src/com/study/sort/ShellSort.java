package com.study.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
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

        shellSort(array);

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String afterSortTime = simpleDateFormat1.format(date1);
        System.out.println("排序前时间为：" + afterSortTime);
    }

    public static void shellSort(int[] arr) {

        //要点，gap 步长，将数组分成 gap 个子序列，所有距离 gap 的元素放在一组
        // 而每个子序列则采用直接插入的排序方式，进行完一轮排序之后，缩小 gap，再重复对子序列进行排序，直到 gap = 0，意味着这已经是一个有序序列
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}
