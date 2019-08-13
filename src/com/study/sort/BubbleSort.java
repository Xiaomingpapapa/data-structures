package com.study.sort;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
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

        sort(array);

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String afterSortTime = simpleDateFormat1.format(date1);
        System.out.println("排序前时间为：" + afterSortTime);


    }

    public static void sort(int[] array) {
        // 要点，将大的数往数组尾部移动，总共需要比较 n -1 轮，n 为元素个数，每轮比较次数都为 n - 1 - i(i 标识第几轮比较)
        //用来表示本趟排序是否有进行交换
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            //如果没有进行交换，则直接退出排序
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }

//        System.out.println("排序后的数组");
//        for (int i : array) {
//            System.out.println(i);
//        }
    }
}
